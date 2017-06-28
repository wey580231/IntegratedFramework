package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.entity.*;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class ScheduleAction extends SuperAction {

    public void beginSchedule() {

        Session session = null;
        Transaction tx = null;

        //初始化数据库表
        try {
            //清空APS数据库
//            String[] tableList = {DatabaseInfo.APS_ORDER, DatabaseInfo.APS_RESOURCE, DatabaseInfo.APS_GROUPRESOURCE, DatabaseInfo.APS_SITE, DatabaseInfo.APS_TYPERESOURCE, DatabaseInfo.APS_SHIFT};
//            Tools.executeSQLForInitTable(DatabaseInfo.MySQL, DatabaseInfo.APS, tableList);

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
            rg_scheduleEntity.setScheduleWindow(rollTimeNodes.asInt());
            //解析APS_Config数据
            JsonNode APS_ConfigNode = rootNode.get("APSConfig");
            for (Iterator<String> it = APS_ConfigNode.fieldNames(); it.hasNext(); ) {
                String APS_ConfigNodeKey = it.next();
                String APS_ConfigNodeValue = APS_ConfigNode.get(APS_ConfigNodeKey).asText();

                if (APS_ConfigNodeKey.equals("t0")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date statTime = sdf.parse(APS_ConfigNodeValue);
                    rg_scheduleEntity.setApsStartTime(statTime);
                }
                if (APS_ConfigNodeKey.equals("t2")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date endTime = sdf.parse(APS_ConfigNodeValue);
                    rg_scheduleEntity.setApsEndTime(endTime);
                }
                if (APS_ConfigNodeKey.equals("objective")) {
                    rg_scheduleEntity.setApsObj(APS_ConfigNodeValue);
                }
                if (APS_ConfigNodeKey.equals("modeScheduling")) {
                    rg_scheduleEntity.setApsModel(APS_ConfigNodeValue);
                }
//                Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertAPSConfigSQL(APS_ConfigNodeKey, APS_ConfigNodeValue));
            }

            session = MySessionFactory.getSessionFactory().getCurrentSession();

            tx = session.getTransaction();
            if (!tx.isActive()) {
                tx = session.beginTransaction();
            }

            //解析Layout数据
            JsonNode layoutNodes = rootNode.get("layout");
            System.out.println(layoutNodes.isArray());
            if (layoutNodes.size() == 1) {
                RG_LayoutEntity layout = session.get(RG_LayoutEntity.class, layoutNodes.get("id").toString());
                rg_scheduleEntity.setLayout(layout);
            }

            //解析订单数据
            JsonNode orderNodes = rootNode.get("orders");
            Set<RG_OrderEntity> rg_orderEntitySet = new HashSet<RG_OrderEntity>();
            for (JsonNode tempNode : orderNodes) {
                RG_OrderEntity rg_orderEntity = session.get(RG_OrderEntity.class, tempNode.get("id").toString());
                if (rg_orderEntity != null) {
                    rg_orderEntitySet.add(rg_orderEntity);
                }
            }
            rg_scheduleEntity.setOrders(rg_orderEntitySet);

            //解析resources数据
            JsonNode resourcesNodes = rootNode.get("resources");
            System.out.println(resourcesNodes.isArray());
            Set<RG_ResourceEntity> rg_resourceEntitySet = new HashSet<RG_ResourceEntity>();
            for (JsonNode tempNode : resourcesNodes) {
                RG_ResourceEntity rg_resourceEntity = session.get(RG_ResourceEntity.class, tempNode.get("id").toString());
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
                RG_GroupresourceEntity rg_groupresourceEntity = session.get(RG_GroupresourceEntity.class, tempNode.get("id").toString());
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
                RG_SiteEntity rg_siteEntity = session.get(RG_SiteEntity.class, tempNode.get("id").toString());
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

            RG_SnapshotNodeEntity middleShot = new RG_SnapshotNodeEntity();
            middleShot.setId(Tools.getUUID());
            middleShot.setName("APS排程结果");
            middleShot.setLevel(SnapshotLevel.MIDDLE);

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

                if (result == ApsTools.STARTED) {
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

    private void printError() {
        Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
    }
}