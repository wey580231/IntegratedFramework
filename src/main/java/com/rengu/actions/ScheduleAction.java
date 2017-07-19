package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.ScheduleDAOImpl;
import com.rengu.entity.*;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class ScheduleAction extends SuperAction {

    public void beginSchedule() {
        try {
            String[] tableNames = {DatabaseInfo.APS_JOB, DatabaseInfo.APS_TASK, DatabaseInfo.APS_LOG, DatabaseInfo.APS_PLAN};
            Tools.executeSQLForInitTable(DatabaseInfo.ORACLE, DatabaseInfo.APS, tableNames);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Session session = null;
        Transaction tx = null;
        //初始化数据库表
        try {
            //更新数据库表内容
            String jsonString = Tools.getHttpRequestBody(this.httpServletRequest);
            JsonNode rootNode = Tools.jsonTreeModelParse(jsonString);
            RG_ScheduleEntity rg_scheduleEntity = new RG_ScheduleEntity();
            rg_scheduleEntity.setId(Tools.getUUID());

            //解析排程名称
            JsonNode nameNodes = rootNode.get("name");
            rg_scheduleEntity.setName(nameNodes.asText());
            rg_scheduleEntity.setState(RG_ScheduleEntity.APS_COMPUTE);

            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            rg_scheduleEntity.setScheduleTime(new java.sql.Date(date.getTime()));
            rg_scheduleEntity.setStartCalcTime(date);
            //解析scheduleWindow
            JsonNode scheduleWindowNodes = rootNode.get("scheduleWindow");
            rg_scheduleEntity.setScheduleWindow(scheduleWindowNodes.asInt());
            //解析rollTime
            JsonNode rollTimeNodes = rootNode.get("rollTime");
            rg_scheduleEntity.setRollTime(rollTimeNodes.asInt());
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

            session = MySessionFactory.getSessionFactory().getCurrentSession();

            tx = session.getTransaction();
            if (!tx.isActive()) {
                tx = session.beginTransaction();
            }

            //解析Layout数据
            JsonNode layoutNodes = rootNode.get("layout");
            if (layoutNodes.size() == 1) {
                RG_LayoutEntity layout = session.get(RG_LayoutEntity.class, layoutNodes.get("id").asText());
                Set<RG_LayoutDetailEntity> rg_layoutDetailEntitySet = layout.getDetails();
                for (RG_LayoutDetailEntity rg_layoutDetailEntity : rg_layoutDetailEntitySet) {
                    System.out.println(rg_layoutDetailEntity.getItem() + "----->LayoutDetail资源名称");
//                    RG_ResourceEntity rg_resourceEntity = session.get(RG_ResourceEntity.class, rg_layoutDetailEntity.getItem());
                }
                rg_scheduleEntity.setLayout(layout);
            }

            //解析订单数据
            JsonNode orderNodes = rootNode.get("orders");
            Set<RG_OrderEntity> rg_orderEntitySet = new HashSet<RG_OrderEntity>();
            for (JsonNode tempNode : orderNodes) {
                RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, tempNode.get("id").asText());
                if (rg_orderEntity != null) {
                    rg_orderEntitySet.add(rg_orderEntity);
                    if (Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, "select * from " + DatabaseInfo.APS_ORDER + " where id='" + rg_orderEntity.getId() + "'").size() == 0) {
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_orderEntity));
                    } else {
                        Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.updateSQLForAPS(rg_orderEntity));
                    }
                }
            }
            rg_scheduleEntity.setOrders(rg_orderEntitySet);

            //解析resources数据
            JsonNode resourcesNodes = rootNode.get("resources");
            Set<RG_ResourceEntity> rg_resourceEntitySet = new HashSet<RG_ResourceEntity>();
            for (JsonNode tempNode : resourcesNodes) {
                RG_ResourceEntity rg_resourceEntity = session.get(RG_ResourceEntity.class, tempNode.get("id").asText());
                if (rg_resourceEntity != null) {
                    rg_resourceEntity.getSchedules().add(rg_scheduleEntity);
                    rg_resourceEntitySet.add(rg_resourceEntity);
                }
            }
            rg_scheduleEntity.setResources(rg_resourceEntitySet);

            //解析groupResource数据
            JsonNode groupResourceNodes = rootNode.get("groupResource");
            Set<RG_GroupresourceEntity> rg_groupresourceEntitySet = new HashSet<RG_GroupresourceEntity>();
            for (JsonNode tempNode : groupResourceNodes) {
                RG_GroupresourceEntity rg_groupresourceEntity = session.get(RG_GroupresourceEntity.class, tempNode.get("id").asText());
                if (rg_groupresourceEntity != null) {
                    rg_groupresourceEntity.getSchedules().add(rg_scheduleEntity);
                    rg_groupresourceEntitySet.add(rg_groupresourceEntity);
                }
            }
            rg_scheduleEntity.setGroups(rg_groupresourceEntitySet);

            //解析Site数据
            JsonNode siteNodes = rootNode.get("site");
            Set<RG_SiteEntity> rg_siteEntitySet = new HashSet<RG_SiteEntity>();
            for (JsonNode tempNode : siteNodes) {
                RG_SiteEntity rg_siteEntity = session.get(RG_SiteEntity.class, tempNode.get("id").asText());
                if (rg_siteEntity != null) {
                    rg_siteEntity.getSchedules().add(rg_scheduleEntity);
                    rg_siteEntitySet.add(rg_siteEntity);
                }
            }
            rg_scheduleEntity.setSites(rg_siteEntitySet);

            //APS ID计算标识
            String apsId = String.valueOf(date.getTime());
            rg_scheduleEntity.setApsFlag(apsId);

            //产生一条快照根记录，并自动生成该记录中
            RG_SnapshotNodeEntity rootSnapshot = new RG_SnapshotNodeEntity();
            rootSnapshot.setId(Tools.getUUID());
            rootSnapshot.setName(rg_scheduleEntity.getName());
            rootSnapshot.setLevel(SnapshotLevel.TOP);
            rootSnapshot.setNodeCreateTime(new Date());

            RG_SnapshotNodeEntity middleShot = new RG_SnapshotNodeEntity();
            middleShot.setId(Tools.getUUID());
            middleShot.setName("APS排程结果");
            middleShot.setLevel(SnapshotLevel.MIDDLE);
            middleShot.setNodeCreateTime(new Date());

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
                    tx.commit();
                    Tools.jsonPrint(Tools.resultCode("ok", "Aps is computing..."), this.httpServletResponse);
                } else {
                    tx.rollback();
                    printError();
                }
            } else {
                tx.rollback();
                printError();
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }
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