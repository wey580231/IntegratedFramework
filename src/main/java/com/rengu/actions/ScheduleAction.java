package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.ScheduleDAOImpl;
import com.rengu.entity.*;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class ScheduleAction extends SuperAction {

    //参数2：排程开始时间
    public static boolean beginScheduleHandler(String jsonString) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1WithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            JsonNode rootNode = Tools.jsonTreeModelParse(jsonString);
            RG_ScheduleEntity rg_scheduleEntity = new RG_ScheduleEntity();
            rg_scheduleEntity.setId(Tools.getUUID());

            //解析排程名称
            JsonNode nameNodes = rootNode.get("name");
            rg_scheduleEntity.setName(nameNodes.asText());
            rg_scheduleEntity.setState(RG_ScheduleEntity.APS_COMPUTE);

            //获取当前时间(解析当前时间)
            JsonNode beginScheduleNode = rootNode.get("BeginScheduleDate");
            Date startDate;
            if (beginScheduleNode == null) {
                //没有开始时间节点
                startDate = new Date();
            } else {
                //有开始事件节点
                startDate = simpleDateFormat1WithTime.parse(beginScheduleNode.asText());
            }
            rg_scheduleEntity.setScheduleTime(new java.sql.Date(startDate.getTime()));
            rg_scheduleEntity.setStartCalcTime(startDate);

            //解析scheduleWindow
            JsonNode scheduleWindowNodes = rootNode.get("scheduleWindow");
            rg_scheduleEntity.setScheduleWindow(scheduleWindowNodes.asInt());

            //解析rollTime
            JsonNode rollTimeNodes = rootNode.get("rollTime");
            rg_scheduleEntity.setRollTime(rollTimeNodes.asInt());

            //解析可容忍天数
            JsonNode scheduleOptionNodes = rootNode.get("scheduleOption");
            rg_scheduleEntity.setScheduleOption(scheduleOptionNodes.asInt());

            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            //获取上次排程信息-处理订单
            if (UserConfigTools.getLatestSchedule("1") != null) {
                RG_ScheduleEntity latestSchedule = session.get(RG_ScheduleEntity.class, UserConfigTools.getLatestSchedule("1"));
                if (latestSchedule != null) {
                    //从上一次排程的订单中清除已经拍成果的订单
                    Set<RG_OrderEntity> rg_orderEntitySet = latestSchedule.getOrders();
                    if (!rg_orderEntitySet.isEmpty()) {
                        List<RG_OrderEntity> removeOrderList = new ArrayList<>();
                        for (RG_OrderEntity rg_orderEntity : rg_orderEntitySet) {
                            //选择待删除订单(结束时间在上次滚动结束之前的订单)
                            if (rg_orderEntity.getT2().before(rg_scheduleEntity.getScheduleTime())) {
                                //设置订单状态为已完成
                                DAOFactory.getOrdersDAOInstance().configOrderState(rg_orderEntity, session, OrderState.Finished);
                                removeOrderList.add(rg_orderEntity);
                            }
                        }
                        Tools.deleteAPSOrder(DatabaseInfo.ORACLE, DatabaseInfo.APS, removeOrderList);
                    }
                    //当前排程滚动周期大于上次泡成滚动周期（添加缺失订单）
                    if (scheduleWindowNodes.asInt() > latestSchedule.getScheduleWindow()) {
                        System.out.println("当前排程滚动周期大于上次排程滚动周期");
                    }
                    //当前排程滚动周期等于上次排程滚动周期
                    if (scheduleWindowNodes.asInt() == latestSchedule.getScheduleWindow()) {
                        System.out.println("当前排程滚动周期等于上次排程滚动周期");
                    }
                    //当前排程滚动周期小于上次排程滚动周期(删除多余订单)
                    if (scheduleWindowNodes.asInt() < latestSchedule.getScheduleWindow()) {
                        System.out.println("当前排程滚动周期小于上次排程滚动周期");
                        //计算当前排程结束时间
                        calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(startDate)));
                        calendar.add(Calendar.DAY_OF_YEAR, scheduleWindowNodes.asInt());
                        Date currentScheduleEndDate = calendar.getTime();
                        //计算当上次程结束时间
                        calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(latestSchedule.getScheduleTime())));
                        calendar.add(Calendar.DAY_OF_YEAR, latestSchedule.getScheduleWindow());
                        Date lastScheduleEndDate = calendar.getTime();
                        //查询需要删除的订单
                        String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.t2 between ? and ?";
                        Query query = session.createQuery(hql);
                        query.setParameter(0, currentScheduleEndDate);
                        query.setParameter(1, lastScheduleEndDate);
                        List<RG_OrderEntity> deleteOrderList = query.list();
                        //删除多余订单
                        if (deleteOrderList.size() > 0) {
                            Tools.deleteAPSOrder(DatabaseInfo.ORACLE, DatabaseInfo.APS, deleteOrderList);
                            //将删除的订单状态改变为计划
                            for (RG_OrderEntity rg_orderEntity : deleteOrderList) {
                                DAOFactory.getOrdersDAOInstance().configOrderState(rg_orderEntity, session, OrderState.Plan);
                            }
                        }
                    }
                }
            }
            //解析订单数据
            JsonNode orderNodes = rootNode.get("orders");
            Set<RG_OrderEntity> rg_orderEntitySet = new HashSet<>();
            for (JsonNode order : orderNodes) {
                RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, order.get("id").asText());
                //设置订单状态为已下发
                DAOFactory.getOrdersDAOInstance().configOrderState(rg_orderEntity, session, OrderState.SendToAPS);
                if (rg_orderEntity != null) {
                    rg_orderEntitySet.add(rg_orderEntity);
                    String sql = "select * from " + DatabaseInfo.APS_ORDER + " where id='" + rg_orderEntity.getId() + "'";
                    //若在APS的订单数据库中找不到
                    if (Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, sql).size() == 0) {
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_orderEntity));
                    }
                }
            }
            rg_scheduleEntity.setOrders(rg_orderEntitySet);

            //解析APS_Config数据
            JsonNode APS_ConfigNode = rootNode.get("APSConfig");
            //查询APS数据的语句
            String SQLString = "select * from " + DatabaseInfo.APS_ORDER;
            //查询APS数据库
            List orderList = Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, SQLString);
            for (Iterator<String> it = APS_ConfigNode.fieldNames(); it.hasNext(); ) {
                String APS_ConfigNodeKey = it.next();
                String APS_ConfigNodeValue = APS_ConfigNode.get(APS_ConfigNodeKey).asText();
                if (APS_ConfigNodeKey.equals("t0")) {
                    //设置前端发送的数据为优化开始时间
                    Date apsStartTime = Tools.parseDate(APS_ConfigNodeValue);
                    //遍历APS的Order表查询最早开始时间
                    for (Object object : orderList) {
                        if (object instanceof HashMap) {
                            Map rowData = (HashMap) object;
                            if (rowData.get("T0") != null) {
                                Date date = Tools.parseStandTextDate(rowData.get("T0").toString().trim());
                                if (apsStartTime.after(date)) {
                                    apsStartTime = date;
                                }
                            }
                        }
                    }
                    calendar.setTime(apsStartTime);
                    calendar.add(Calendar.DAY_OF_YEAR, -2);
                    apsStartTime = calendar.getTime();
                    System.out.println("优化开始时间：" + apsStartTime);
                    rg_scheduleEntity.setApsStartTime(apsStartTime);
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, Tools.dateConvertToString(rg_scheduleEntity.getApsStartTime())));
                }
                if (APS_ConfigNodeKey.equals("t2")) {
                    //设置前端发送的数据为优化结束时间
                    Date apsEndTime = Tools.parseDate(APS_ConfigNodeValue);
                    //遍历APS的Order表查询最晚结束时间
                    for (Object object : orderList) {
                        if (object instanceof HashMap) {
                            Map rowData = (HashMap) object;
                            Date date = Tools.parseStandTextDate(rowData.get("T2").toString().trim());
                            if (apsEndTime.before(date)) {
                                apsEndTime = date;

                            }
                        }
                    }
                    calendar.setTime(apsEndTime);
                    calendar.add(Calendar.DAY_OF_YEAR, 5);
                    apsEndTime = calendar.getTime();
                    System.out.println("优化结束时间：" + apsEndTime);
                    rg_scheduleEntity.setApsEndTime(apsEndTime);
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, Tools.dateConvertToString(rg_scheduleEntity.getApsEndTime())));
                }
                if (APS_ConfigNodeKey.equals("modeScheduling")) {
                    rg_scheduleEntity.setApsModel(APS_ConfigNodeValue);
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateAPSConfigSQL(APS_ConfigNodeKey, APS_ConfigNodeValue));
                }
            }

            //解析Layout数据
            JsonNode layoutNodes = rootNode.get("layout");
            RG_LayoutEntity layout = session.get(RG_LayoutEntity.class, layoutNodes.get("id").asText());
            Set<RG_LayoutDetailEntity> rg_layoutDetailEntitySet = layout.getDetails();
            //更新资源可用情况
            int znzpNum = 0, rjxzNum = 0;
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
                    String SQlString = "update APS_RESOURCE set STATE = '0' where id = '" + rg_layoutDetailEntity.getItem() + "'";
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, SQlString);
                    MyLog.getLogger().info(rg_layoutDetailEntity.getItem() + "资源不可用");
                }
            }
            //清空数据库表
            Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "truncate table APS_SITE");
            Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, "truncate table APS_SITE_SITE");

            //基础布局【1】
            int selectedLayoutId = 1;
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
            //APS ID计算标识
            String apsId = String.valueOf(startDate.getTime());
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
            rootSnapshot.setNodeCreateTime(startDate);

            //中间节点快照
            RG_SnapshotNodeEntity middleShot = new RG_SnapshotNodeEntity();
            middleShot.setId(Tools.getUUID());
            middleShot.setName("静态优化");
            middleShot.setLevel(SnapshotLevel.MIDDLE);
            middleShot.setNodeCreateTime(startDate);
            middleShot.setApply(false);
            middleShot.setErrorNode(false);
            middleShot.setFirstNode(true);
            middleShot.setApsBackupSnaoshot(false);
            middleShot.setApsDispatchOrder(false);
            middleShot.setApsRecoverSnapshot(false);
            middleShot.setParent(rootSnapshot);
            middleShot.setRootParent(rootSnapshot);
            rootSnapshot.getChilds().add(middleShot);
            //建立快照节点和排程记录的关联
            rootSnapshot.setSchedule(rg_scheduleEntity);
            rg_scheduleEntity.setSnapshot(rootSnapshot);
            //更新用户排程信息
            int updateResult = UserConfigTools.newScheduleRecord("1", rg_scheduleEntity.getId(), rootSnapshot.getId(), middleShot.getId(), false);
            if (updateResult > 0) {
                session.save(rg_scheduleEntity);
                //用户新建排程时，需要将当前用户的排程记录记为0
                UserConfigTools.updateApsReplyCount("1", 0);
                //调用排程接口
                int result = ApsTools.instance().startAPSSchedule(middleShot.getId());
                if (result == ApsTools.STARTED) {
                    //更新订单状态为计算中
                    for (JsonNode tempNode : orderNodes) {
                        RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, tempNode.get("id").asText());
                        if (rg_orderEntity != null) {
                            DAOFactory.getOrdersDAOInstance().configOrderState(rg_orderEntity, session, OrderState.Calculating);
                        }
                    }
                    //更新事件日志节点
                    Tools.createEventLog(session, EventLogTools.ScheduleCreateEvent, EventLogTools.StandardTimeLineItem, rg_scheduleEntity.getName() + "-APS计算已启动", EventLogTools.createScheduleStartEventContent(rg_scheduleEntity), rg_scheduleEntity.getId());
                    transaction.commit();
                } else {
                    transaction.rollback();
                    return false;
                }
            } else {
                transaction.rollback();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void beginSchedule() {
        //重网页获取排程信息
        String jsonString = Tools.getHttpRequestBody(this.httpServletRequest);
        if (beginScheduleHandler(jsonString)) {
            Tools.jsonPrint(Tools.resultCode("ok", "Aps is computing..."), this.httpServletResponse);
        } else {
            printError();
        }
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