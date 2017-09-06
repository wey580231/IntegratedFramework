package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.ScheduleDAOImpl;
import com.rengu.entity.*;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.*;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class ScheduleAction extends SuperAction {

    public void beginSchedule() {
        //初始化APS数据库
//        try {
//            String[] tableNames = {DatabaseInfo.APS_JOB, DatabaseInfo.APS_TASK, DatabaseInfo.APS_LOG, DatabaseInfo.APS_PLAN, DatabaseInfo.APS_ORDER};
//            Tools.executeSQLForInitTable(DatabaseInfo.ORACLE, DatabaseInfo.APS, tableNames);
//            Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "update APS_RESOURCE set STATE = '0'");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //更新数据库表内容
        String jsonString = Tools.getHttpRequestBody(this.httpServletRequest);

        if (parseAndSnaphost(jsonString)) {
            Tools.jsonPrint(Tools.resultCode("ok", "Aps is computing..."), this.httpServletResponse);
        } else {
            printError();
        }
    }

    public boolean parseAndSnaphost(String orginJson, Date startRollingTime) {
        Session session = null;
        Transaction tx = null;
        //初始化数据库表
        try {
            JsonNode rootNode = Tools.jsonTreeModelParse(orginJson);
            RG_ScheduleEntity rg_scheduleEntity = new RG_ScheduleEntity();
            rg_scheduleEntity.setId(Tools.getUUID());

            //解析排程名称
            JsonNode nameNodes = rootNode.get("name");
            rg_scheduleEntity.setName(nameNodes.asText());
            rg_scheduleEntity.setState(RG_ScheduleEntity.APS_COMPUTE);

            //获取当前时间
            Date date = startRollingTime;
            rg_scheduleEntity.setScheduleTime(new java.sql.Date(date.getTime()));
            rg_scheduleEntity.setStartCalcTime(date);

            //解析scheduleWindow
            JsonNode scheduleWindowNodes = rootNode.get("scheduleWindow");
            rg_scheduleEntity.setScheduleWindow(scheduleWindowNodes.asInt());

            //解析rollTime
            JsonNode rollTimeNodes = rootNode.get("rollTime");
            rg_scheduleEntity.setRollTime(rollTimeNodes.asInt());

            //解析可容忍天数
            JsonNode scheduleOptionNodes = rootNode.get("scheduleOption");
            rg_scheduleEntity.setScheduleOption(scheduleOptionNodes.asInt());

            //Yang 20170901查询上次排程的时间窗信息
            String latesScheduleId = UserConfigTools.getLatestSchedule("1");

            session = MySessionFactory.getSessionFactory().getCurrentSession();

            tx = session.getTransaction();
            if (!tx.isActive()) {
                tx = session.beginTransaction();
            }

            JsonNode orderNodes = rootNode.get("orders");
            Set<RG_OrderEntity> rg_orderEntitySet = new HashSet<RG_OrderEntity>();

            RG_ScheduleEntity latestSchedule = null;

            if (latesScheduleId != null) {
                latestSchedule = session.get(RG_ScheduleEntity.class, latesScheduleId);
            }

            if (latestSchedule != null) {
                int lastScheduleWindow = latestSchedule.getScheduleWindow().intValue();
                int lastScheduleRollTime = latestSchedule.getRollTime().intValue();

                if (scheduleWindowNodes.asInt() > lastScheduleWindow) {

                } else if (scheduleWindowNodes.asInt() < lastScheduleWindow) {
                    Date lastScheduleDate = latestSchedule.getScheduleTime();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(lastScheduleDate);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + lastScheduleWindow);
                    Date lastScheduleEndTime = calendar.getTime();

                    Date currDate = startRollingTime;
                    Calendar currClendar = Calendar.getInstance();
                    currClendar.setTime(currDate);
                    currClendar.set(Calendar.HOUR_OF_DAY, 0);
                    currClendar.set(Calendar.MINUTE, 0);
                    currClendar.set(Calendar.SECOND, 0);
                    currClendar.set(Calendar.DAY_OF_MONTH, currClendar.get(Calendar.DAY_OF_MONTH) + scheduleWindowNodes.asInt());
                    Date currScheduleEndTime = currClendar.getTime();

                    NativeQuery tmpQuery = session.createNativeQuery("select * from rg_order entity where entity.t2 between ? and ? ", RG_OrderEntity.class);
                    tmpQuery.setParameter(1, Tools.formatToStandardDate(currScheduleEndTime));
                    tmpQuery.setParameter(2, Tools.formatToStandardDate(lastScheduleEndTime));

                    List<RG_OrderEntity> list = tmpQuery.list();
                    if (list.size() > 0) {
                        ApsTools.isOrderDeleted = true;
                        int tmpState = ApsTools.instance().deleteOrder(list);

                        if (tmpState == ApsTools.STARTED) {
                            int tmpCount = 0;
                            while (ApsTools.isOrderDeleted && tmpCount < 30) {
                                try {
                                    tmpCount++;

                                    System.out.println("=========等待结果=========");

                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (ApsTools.isOrderDeleted || tmpCount >= 30) {
                                throw new Exception();
                            }
                        }
                    }
                } else if (lastScheduleWindow == scheduleWindowNodes.asInt()) {

                }
            }

            for (JsonNode tempNode : orderNodes) {
                RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, tempNode.get("id").asText());
                if (rg_orderEntity != null) {
                    rg_orderEntitySet.add(rg_orderEntity);
                    String sql = "select * from " + DatabaseInfo.APS_ORDER + " where id='" + rg_orderEntity.getId() + "'";
                    if (Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, sql).size() == 0) {
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_orderEntity));
                    }
//                    else {
//                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateSQLForAPS(rg_orderEntity));
//                    }
                }
            }
            rg_scheduleEntity.setOrders(rg_orderEntitySet);

            //解析APS_Config数据
            JsonNode APS_ConfigNode = rootNode.get("APSConfig");
            for (Iterator<String> it = APS_ConfigNode.fieldNames(); it.hasNext(); ) {
                String APS_ConfigNodeKey = it.next();
                String APS_ConfigNodeValue = APS_ConfigNode.get(APS_ConfigNodeKey).asText();
                if (APS_ConfigNodeKey.equals("t0")) {
                    rg_scheduleEntity.setApsStartTime(Tools.parseDate(APS_ConfigNodeValue));
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, Tools.dateConvertToString(rg_scheduleEntity.getApsStartTime())));
                }
                if (APS_ConfigNodeKey.equals("t2")) {
                    rg_scheduleEntity.setApsEndTime(Tools.parseDate(APS_ConfigNodeValue));
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, Tools.dateConvertToString(rg_scheduleEntity.getApsEndTime())));
                }
                if (APS_ConfigNodeKey.equals("modeScheduling")) {
                    rg_scheduleEntity.setApsModel(APS_ConfigNodeValue);
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, APS_ConfigNodeValue));
                }
            }

            //解析Layout数据
            JsonNode layoutNodes = rootNode.get("layout");
            if (layoutNodes.size() == 1) {
                RG_LayoutEntity layout = session.get(RG_LayoutEntity.class, layoutNodes.get("id").asText());
                Set<RG_LayoutDetailEntity> rg_layoutDetailEntitySet = layout.getDetails();

                //开启所有资源
//                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "update APS_RESOURCE set STATE = '1'");

                int znzpNum = 0, rjxzNum = 0;

                //更新资源可用情况
                for (RG_LayoutDetailEntity rg_layoutDetailEntity : rg_layoutDetailEntitySet) {
                    String resourceExist = rg_layoutDetailEntity.getExist();
                    String resourceState = rg_layoutDetailEntity.getState();
                    if (resourceExist.equals("true") && resourceState.equals("running")) {

                        if (rg_layoutDetailEntity.getItem().contains("ZNZPPT")) {
                            znzpNum++;
                        } else if (rg_layoutDetailEntity.getItem().contains("RJXZPT")) {
                            rjxzNum++;
                        }

                        String SQlString = "update APS_RESOURCE set STATE = '1' where id = '" + rg_layoutDetailEntity.getItem() + "'";
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, SQlString);
                    } else {
                        MyLog.getLogger().info(rg_layoutDetailEntity.getItem() + "资源不可用");
                    }
                }

                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "truncate table APS_SITE");
                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "truncate table APS_SITE_SITE");

                int selectedLayoutId = 1;

                //基础布局【1】
                if (znzpNum == 1 && rjxzNum == 1) {
                    selectedLayoutId = 1;
                }
                //2个智能装配，1个人机协作【2】
                else if (znzpNum == 2 && rjxzNum == 1) {
                    selectedLayoutId = 2;
                }
                //1个/2个智能装配，2个人机协作【3】
                else if ((znzpNum == 2 || znzpNum == 1) && rjxzNum == 2) {
                    selectedLayoutId = 3;
                }

                //【1】更新APS的site表
                Query query = session.createQuery("From RG_SiteTypeEntity where layoutType = '" + selectedLayoutId + "'", RG_SiteTypeEntity.class);
                List<RG_SiteTypeEntity> siteTypes = query.list();
                if (siteTypes.size() > 0) {
                    Iterator<RG_SiteTypeEntity> iter = siteTypes.iterator();
                    while (iter.hasNext()) {
                        RG_SiteTypeEntity siteType = iter.next();

                        String sql = "INSERT INTO APS_SITE VALUES ('%s','%s',%d,%d,'%s','%s',%d,%d,'%s')";
                        sql = String.format(sql, siteType.getResource(), siteType.getName(), siteType.getX(), siteType.getY(), siteType.getType(), siteType.getIdIcon(), siteType.getSizeIcon(), siteType.getCapacity(), siteType.getColor());
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, sql);
                    }
                }

                //【2】更新APS的site-site表
                query = session.createQuery("From RG_LayoutDistanceEntity where type = '" + selectedLayoutId + "' ", RG_LayoutDistanceEntity.class);
                List<RG_LayoutDistanceEntity> layoutDetails = query.list();
                if (layoutDetails.size() > 0) {
                    Iterator<RG_LayoutDistanceEntity> iter = layoutDetails.iterator();
                    while (iter.hasNext()) {
                        RG_LayoutDistanceEntity siteType = iter.next();

                        String sql = "INSERT INTO APS_SITE_SITE VALUES ('%s','%s',%d,%d )";

                        if (siteType.getTime().length() == 0) {
                            sql = String.format(sql, siteType.getSite1(), siteType.getSite2(), 0, Integer.parseInt(siteType.getDistance()));
                        } else {
                            sql = String.format(sql, siteType.getSite1(), siteType.getSite2(), Integer.parseInt(siteType.getTime()), Integer.parseInt(siteType.getDistance()));
                        }

                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, sql);
                    }
                }

                //【3】更新框架的distance表
                NativeQuery nq = session.createNativeQuery("truncate table rg_distance");
                if (nq.executeUpdate() >= 0) {
                    Iterator<RG_LayoutDistanceEntity> iter = layoutDetails.iterator();
                    while (iter.hasNext()) {
                        RG_LayoutDistanceEntity siteType = iter.next();

                        RG_DistanceEntity distanceEntity = new RG_DistanceEntity();
                        distanceEntity.setId(Tools.getUUID());
                        distanceEntity.setStartSite(session.get(RG_SiteEntity.class, siteType.getSite1()));
                        distanceEntity.setEndSite(session.get(RG_SiteEntity.class, siteType.getSite2()));
                        try {
                            distanceEntity.setTime(Integer.parseInt(siteType.getTime()));
                        } catch (NumberFormatException e) {
                            distanceEntity.setTime(0);
                        }

                        try {
                            distanceEntity.setDistance(Integer.parseInt(siteType.getDistance()));
                        } catch (NumberFormatException e) {
                            distanceEntity.setDistance(0);
                        }
                        session.save(distanceEntity);
                    }
                }

                //将托盘资源职位可用状态
                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "update APS_RESOURCE set STATE = '1' where id = 'XZTP01_01'");
                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "update APS_RESOURCE set STATE = '1' where id = 'XZTP02_02'");
                rg_scheduleEntity.setLayout(layout);
            }

            //APS ID计算标识
            String apsId = String.valueOf(date.getTime());
            rg_scheduleEntity.setApsFlag(apsId);

            //产生一条快照根记录，并自动生成该记录中
            RG_SnapshotNodeEntity rootSnapshot = new RG_SnapshotNodeEntity();
            rootSnapshot.setId(Tools.getUUID());
            rootSnapshot.setName(rg_scheduleEntity.getName());
            rootSnapshot.setLevel(SnapshotLevel.TOP);
            rootSnapshot.setApply(false);
            rootSnapshot.setErrorNode(false);
            rootSnapshot.setFirstNode(false);
            rootSnapshot.setApsBackupSnaoshot(false);
            rootSnapshot.setApsDispatchOrder(false);
            rootSnapshot.setApsRecoverSnapshot(false);
            rootSnapshot.setNodeCreateTime(new Date());

            RG_SnapshotNodeEntity middleShot = new RG_SnapshotNodeEntity();
            middleShot.setId(Tools.getUUID());
            middleShot.setName("静态优化");
            middleShot.setLevel(SnapshotLevel.MIDDLE);
            middleShot.setNodeCreateTime(startRollingTime);
            middleShot.setApply(false);
            middleShot.setErrorNode(false);
            middleShot.setFirstNode(true);
            middleShot.setApsBackupSnaoshot(false);
            middleShot.setApsDispatchOrder(false);
            middleShot.setApsRecoverSnapshot(false);

            middleShot.setParent(rootSnapshot);
            middleShot.setRootParent(rootSnapshot);

            rootSnapshot.getChilds().add(middleShot);
            rootSnapshot.setSchedule(rg_scheduleEntity);
            rg_scheduleEntity.setSnapshot(rootSnapshot);

            //TODO 需要前端传入当前创建排程用户的ID信息
            int updateResult = UserConfigTools.newScheduleRecord("1", rg_scheduleEntity.getId(), rootSnapshot.getId(), middleShot.getId(), false);
            if (updateResult > 0) {
                session.save(rg_scheduleEntity);

                //用户新建排程时，需要将当前用户的排程记录记为0
                UserConfigTools.updateApsReplyCount("1", 0);

                //调用排程接口
                int result = ApsTools.instance().startAPSSchedule(middleShot.getId());
                rg_scheduleEntity.setOrders(rg_orderEntitySet);
                if (result == ApsTools.STARTED) {
                    //更新订单状态
                    for (JsonNode tempNode : orderNodes) {
                        RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, tempNode.get("id").asText());
                        if (rg_orderEntity != null) {
                            rg_orderEntity.setState(Byte.parseByte("1"));
                            session.save(rg_orderEntity);
                        }
                    }
                    //更新事件日志节点
                    Tools.createEventLog(session, EventLogTools.ScheduleCreateEvent, EventLogTools.StandardTimeLineItem, rg_scheduleEntity.getName() + "-APS计算已启动", EventLogTools.createScheduleStartEventContent(rg_scheduleEntity), rg_scheduleEntity.getId());
                    tx.commit();
                } else {
                    tx.rollback();
                    return false;
                }
            } else {
                tx.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return true;
    }

    public boolean parseAndSnaphost(String orginJson) {
        Session session = null;
        Transaction tx = null;
        //初始化数据库表
        try {
            JsonNode rootNode = Tools.jsonTreeModelParse(orginJson);
            RG_ScheduleEntity rg_scheduleEntity = new RG_ScheduleEntity();
            rg_scheduleEntity.setId(Tools.getUUID());

            //解析排程名称
            JsonNode nameNodes = rootNode.get("name");
            rg_scheduleEntity.setName(nameNodes.asText());
            rg_scheduleEntity.setState(RG_ScheduleEntity.APS_COMPUTE);

            //获取当前时间
            Date date = new Date();
            rg_scheduleEntity.setScheduleTime(new java.sql.Date(date.getTime()));
            rg_scheduleEntity.setStartCalcTime(date);

            //解析scheduleWindow
            JsonNode scheduleWindowNodes = rootNode.get("scheduleWindow");
            rg_scheduleEntity.setScheduleWindow(scheduleWindowNodes.asInt());

            //解析rollTime
            JsonNode rollTimeNodes = rootNode.get("rollTime");
            rg_scheduleEntity.setRollTime(rollTimeNodes.asInt());

            //解析可容忍天数
            JsonNode scheduleOptionNodes = rootNode.get("scheduleOption");
            rg_scheduleEntity.setScheduleOption(scheduleOptionNodes.asInt());

            //Yang 20170901查询上次排程的时间窗信息
            String latesScheduleId = UserConfigTools.getLatestSchedule("1");

            session = MySessionFactory.getSessionFactory().getCurrentSession();

            tx = session.getTransaction();
            if (!tx.isActive()) {
                tx = session.beginTransaction();
            }

            JsonNode orderNodes = rootNode.get("orders");
            Set<RG_OrderEntity> rg_orderEntitySet = new HashSet<RG_OrderEntity>();

            RG_ScheduleEntity latestSchedule = null;

            if (latesScheduleId != null) {
                latestSchedule = session.get(RG_ScheduleEntity.class, latesScheduleId);
            }

            if (latestSchedule != null) {
                int lastScheduleWindow = latestSchedule.getScheduleWindow().intValue();
                int lastScheduleRollTime = latestSchedule.getRollTime().intValue();

                if (scheduleWindowNodes.asInt() > lastScheduleWindow) {

                } else if (scheduleWindowNodes.asInt() < lastScheduleWindow) {
                    Date lastScheduleDate = latestSchedule.getScheduleTime();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(lastScheduleDate);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + lastScheduleWindow);
                    Date lastScheduleEndTime = calendar.getTime();

                    Date currDate = new Date();
                    Calendar currClendar = Calendar.getInstance();
                    currClendar.setTime(currDate);
                    currClendar.set(Calendar.HOUR_OF_DAY, 0);
                    currClendar.set(Calendar.MINUTE, 0);
                    currClendar.set(Calendar.SECOND, 0);
                    currClendar.set(Calendar.DAY_OF_MONTH, currClendar.get(Calendar.DAY_OF_MONTH) + scheduleWindowNodes.asInt());
                    Date currScheduleEndTime = currClendar.getTime();

                    NativeQuery tmpQuery = session.createNativeQuery("select * from rg_order entity where entity.t2 between ? and ? ", RG_OrderEntity.class);
                    tmpQuery.setParameter(1, Tools.formatToStandardDate(currScheduleEndTime));
                    tmpQuery.setParameter(2, Tools.formatToStandardDate(lastScheduleEndTime));

                    List<RG_OrderEntity> list = tmpQuery.list();
                    if (list.size() > 0) {
                        ApsTools.isOrderDeleted = true;
                        int tmpState = ApsTools.instance().deleteOrder(list);

                        if (tmpState == ApsTools.STARTED) {
                            int tmpCount = 0;
                            while (ApsTools.isOrderDeleted && tmpCount < 30) {
                                try {
                                    tmpCount++;

                                    System.out.println("=========等待结果=========");

                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (ApsTools.isOrderDeleted || tmpCount >= 30) {
                                throw new Exception();
                            }
                        }
                    }
                } else if (lastScheduleWindow == scheduleWindowNodes.asInt()) {

                }
            }

            for (JsonNode tempNode : orderNodes) {
                RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, tempNode.get("id").asText());
                if (rg_orderEntity != null) {
                    rg_orderEntitySet.add(rg_orderEntity);
                    String sql = "select * from " + DatabaseInfo.APS_ORDER + " where id='" + rg_orderEntity.getId() + "'";
                    if (Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, sql).size() == 0) {
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_orderEntity));
                    }
//                    else {
//                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateSQLForAPS(rg_orderEntity));
//                    }
                }
            }
            rg_scheduleEntity.setOrders(rg_orderEntitySet);

            //解析APS_Config数据
            JsonNode APS_ConfigNode = rootNode.get("APSConfig");
            for (Iterator<String> it = APS_ConfigNode.fieldNames(); it.hasNext(); ) {
                String APS_ConfigNodeKey = it.next();
                String APS_ConfigNodeValue = APS_ConfigNode.get(APS_ConfigNodeKey).asText();
                if (APS_ConfigNodeKey.equals("t0")) {
                    rg_scheduleEntity.setApsStartTime(Tools.parseDate(APS_ConfigNodeValue));
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, Tools.dateConvertToString(rg_scheduleEntity.getApsStartTime())));
                }
                if (APS_ConfigNodeKey.equals("t2")) {
                    rg_scheduleEntity.setApsEndTime(Tools.parseDate(APS_ConfigNodeValue));
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, Tools.dateConvertToString(rg_scheduleEntity.getApsEndTime())));
                }
                if (APS_ConfigNodeKey.equals("modeScheduling")) {
                    rg_scheduleEntity.setApsModel(APS_ConfigNodeValue);
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, APS_ConfigNodeValue));
                }
            }

            //解析Layout数据
            JsonNode layoutNodes = rootNode.get("layout");
            if (layoutNodes.size() == 1) {
                RG_LayoutEntity layout = session.get(RG_LayoutEntity.class, layoutNodes.get("id").asText());
                Set<RG_LayoutDetailEntity> rg_layoutDetailEntitySet = layout.getDetails();

                //开启所有资源
//                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "update APS_RESOURCE set STATE = '1'");

                int znzpNum = 0, rjxzNum = 0;

                //更新资源可用情况
                for (RG_LayoutDetailEntity rg_layoutDetailEntity : rg_layoutDetailEntitySet) {
                    String resourceExist = rg_layoutDetailEntity.getExist();
                    String resourceState = rg_layoutDetailEntity.getState();
                    if (resourceExist.equals("true") && resourceState.equals("running")) {

                        if (rg_layoutDetailEntity.getItem().contains("ZNZPPT")) {
                            znzpNum++;
                        } else if (rg_layoutDetailEntity.getItem().contains("RJXZPT")) {
                            rjxzNum++;
                        }

                        String SQlString = "update APS_RESOURCE set STATE = '1' where id = '" + rg_layoutDetailEntity.getItem() + "'";
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, SQlString);
                    } else {
                        MyLog.getLogger().info(rg_layoutDetailEntity.getItem() + "资源不可用");
                    }
                }

                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "truncate table APS_SITE");
                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "truncate table APS_SITE_SITE");

                int selectedLayoutId = 1;

                //基础布局【1】
                if (znzpNum == 1 && rjxzNum == 1) {
                    selectedLayoutId = 1;
                }
                //2个智能装配，1个人机协作【2】
                else if (znzpNum == 2 && rjxzNum == 1) {
                    selectedLayoutId = 2;
                }
                //1个/2个智能装配，2个人机协作【3】
                else if ((znzpNum == 2 || znzpNum == 1) && rjxzNum == 2) {
                    selectedLayoutId = 3;
                }

                //【1】更新APS的site表
                Query query = session.createQuery("From RG_SiteTypeEntity where layoutType = '" + selectedLayoutId + "'", RG_SiteTypeEntity.class);
                List<RG_SiteTypeEntity> siteTypes = query.list();
                if (siteTypes.size() > 0) {
                    Iterator<RG_SiteTypeEntity> iter = siteTypes.iterator();
                    while (iter.hasNext()) {
                        RG_SiteTypeEntity siteType = iter.next();

                        String sql = "INSERT INTO APS_SITE VALUES ('%s','%s',%d,%d,'%s','%s',%d,%d,'%s')";
                        sql = String.format(sql, siteType.getResource(), siteType.getName(), siteType.getX(), siteType.getY(), siteType.getType(), siteType.getIdIcon(), siteType.getSizeIcon(), siteType.getCapacity(), siteType.getColor());
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, sql);
                    }
                }

                //【2】更新APS的site-site表
                query = session.createQuery("From RG_LayoutDistanceEntity where type = '" + selectedLayoutId + "' ", RG_LayoutDistanceEntity.class);
                List<RG_LayoutDistanceEntity> layoutDetails = query.list();
                if (layoutDetails.size() > 0) {
                    Iterator<RG_LayoutDistanceEntity> iter = layoutDetails.iterator();
                    while (iter.hasNext()) {
                        RG_LayoutDistanceEntity siteType = iter.next();

                        String sql = "INSERT INTO APS_SITE_SITE VALUES ('%s','%s',%d,%d )";

                        if (siteType.getTime().length() == 0) {
                            sql = String.format(sql, siteType.getSite1(), siteType.getSite2(), 0, Integer.parseInt(siteType.getDistance()));
                        } else {
                            sql = String.format(sql, siteType.getSite1(), siteType.getSite2(), Integer.parseInt(siteType.getTime()), Integer.parseInt(siteType.getDistance()));
                        }

                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, sql);
                    }
                }

                //【3】更新框架的distance表
                NativeQuery nq = session.createNativeQuery("truncate table rg_distance");
                if (nq.executeUpdate() >= 0) {
                    Iterator<RG_LayoutDistanceEntity> iter = layoutDetails.iterator();
                    while (iter.hasNext()) {
                        RG_LayoutDistanceEntity siteType = iter.next();

                        RG_DistanceEntity distanceEntity = new RG_DistanceEntity();
                        distanceEntity.setId(Tools.getUUID());
                        distanceEntity.setStartSite(session.get(RG_SiteEntity.class, siteType.getSite1()));
                        distanceEntity.setEndSite(session.get(RG_SiteEntity.class, siteType.getSite2()));
                        try {
                            distanceEntity.setTime(Integer.parseInt(siteType.getTime()));
                        } catch (NumberFormatException e) {
                            distanceEntity.setTime(0);
                        }

                        try {
                            distanceEntity.setDistance(Integer.parseInt(siteType.getDistance()));
                        } catch (NumberFormatException e) {
                            distanceEntity.setDistance(0);
                        }
                        session.save(distanceEntity);
                    }
                }

                //将托盘资源职位可用状态
                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "update APS_RESOURCE set STATE = '1' where id = 'XZTP01_01'");
                Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "update APS_RESOURCE set STATE = '1' where id = 'XZTP02_02'");
                rg_scheduleEntity.setLayout(layout);
            }

            //APS ID计算标识
            String apsId = String.valueOf(date.getTime());
            rg_scheduleEntity.setApsFlag(apsId);

            //产生一条快照根记录，并自动生成该记录中
            RG_SnapshotNodeEntity rootSnapshot = new RG_SnapshotNodeEntity();
            rootSnapshot.setId(Tools.getUUID());
            rootSnapshot.setName(rg_scheduleEntity.getName());
            rootSnapshot.setLevel(SnapshotLevel.TOP);
            rootSnapshot.setApply(false);
            rootSnapshot.setErrorNode(false);
            rootSnapshot.setFirstNode(false);
            rootSnapshot.setApsBackupSnaoshot(false);
            rootSnapshot.setApsDispatchOrder(false);
            rootSnapshot.setApsRecoverSnapshot(false);
            rootSnapshot.setNodeCreateTime(new Date());

            RG_SnapshotNodeEntity middleShot = new RG_SnapshotNodeEntity();
            middleShot.setId(Tools.getUUID());
            middleShot.setName("静态优化");
            middleShot.setLevel(SnapshotLevel.MIDDLE);
            middleShot.setNodeCreateTime(new Date());
            middleShot.setApply(false);
            middleShot.setErrorNode(false);
            middleShot.setFirstNode(true);
            middleShot.setApsBackupSnaoshot(false);
            middleShot.setApsDispatchOrder(false);
            middleShot.setApsRecoverSnapshot(false);

            middleShot.setParent(rootSnapshot);
            middleShot.setRootParent(rootSnapshot);

            rootSnapshot.getChilds().add(middleShot);
            rootSnapshot.setSchedule(rg_scheduleEntity);
            rg_scheduleEntity.setSnapshot(rootSnapshot);

            //TODO 需要前端传入当前创建排程用户的ID信息
            int updateResult = UserConfigTools.newScheduleRecord("1", rg_scheduleEntity.getId(), rootSnapshot.getId(), middleShot.getId(), false);
            if (updateResult > 0) {
                session.save(rg_scheduleEntity);

                //用户新建排程时，需要将当前用户的排程记录记为0
                UserConfigTools.updateApsReplyCount("1", 0);

                //调用排程接口
                int result = ApsTools.instance().startAPSSchedule(middleShot.getId());
                rg_scheduleEntity.setOrders(rg_orderEntitySet);
                if (result == ApsTools.STARTED) {
                    //更新订单状态
                    for (JsonNode tempNode : orderNodes) {
                        RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, tempNode.get("id").asText());
                        if (rg_orderEntity != null) {
                            rg_orderEntity.setState(Byte.parseByte("1"));
                            session.save(rg_orderEntity);
                        }
                    }
                    //更新事件日志节点
                    Tools.createEventLog(session, EventLogTools.ScheduleCreateEvent, EventLogTools.StandardTimeLineItem, rg_scheduleEntity.getName() + "-APS计算已启动", EventLogTools.createScheduleStartEventContent(rg_scheduleEntity), rg_scheduleEntity.getId());
                    tx.commit();
                } else {
                    tx.rollback();
                    return false;
                }
            } else {
                tx.rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return true;
    }

    //获取所有的排程
    public void getAllSchedules() throws Exception {
        ScheduleDAOImpl scheduleDAO = DAOFactory.getScheduleDAOImplInstance();
        List list = scheduleDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    private void printError() {
        Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
    }

    public void delete() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        String scheduleId = jsonNode.get("id").asText();
        RG_ScheduleEntity rg_scheduleEntity = DAOFactory.getScheduleDAOImplInstance().findAllById(scheduleId);
        DAOFactory.getScheduleDAOImplInstance().delete(rg_scheduleEntity);
    }
}