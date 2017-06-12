package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.*;
import com.rengu.entity.*;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class ScheduleAction extends SuperAction {

    public void beginSchedule() throws Exception {
        String jsonString = Tools.getHttpRequestBody(this.httpServletRequest);
        JsonNode rootNode = Tools.jsonTreeModelParse(jsonString);
        RG_ScheduleEntity rg_scheduleEntity = new RG_ScheduleEntity();
        //解析排程名称
        JsonNode nameNodes = rootNode.get("name");
        rg_scheduleEntity.setName(nameNodes.asText());
        //获取当前时间
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String scheduleTime = dateFormat.format(date);
        rg_scheduleEntity.setScheduleTime(scheduleTime);
        rg_scheduleEntity.setStartCalcTime(scheduleTime);
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
                rg_scheduleEntity.setApsStartTime(APS_ConfigNodeValue);
            }
            if (APS_ConfigNodeKey.equals("t2")) {
                rg_scheduleEntity.setApsEndTime(APS_ConfigNodeValue);
            }
            if (APS_ConfigNodeKey.equals("objective")) {
                rg_scheduleEntity.setApsObj(APS_ConfigNodeValue);
            }
            if (APS_ConfigNodeKey.equals("modeScheduling")) {
                rg_scheduleEntity.setApsModel(APS_ConfigNodeValue);
            }
//            Tools.executeSQLForUpdate(Info.MySQL, Info.APS, APSTools.insertAPSConfigSQL(APS_ConfigNodeKey, APS_ConfigNodeValue));
        }
        //解析Layout数据
        JsonNode layoutNode = rootNode.get("layout");
        RG_LayoutEntity rg_layoutEntityWithId = Tools.jsonConvertToEntity(layoutNode.toString(), RG_LayoutEntity.class);
        LayoutDAOImpl layoutDAO = DAOFactory.getLayoutDAOImplInstance();
        RG_LayoutEntity rg_layoutEntity = (RG_LayoutEntity) layoutDAO.findAllById(rg_layoutEntityWithId).get(0);
        rg_scheduleEntity.setLayout(rg_layoutEntity);
        layoutDAO.getTransaction().commit();
        //解析订单数据
        JsonNode orderNodes = rootNode.get("orders");
        Set<RG_OrderEntity> rg_orderEntitySet = new HashSet<>();
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        for (JsonNode tempNode : orderNodes) {
            String orderNodeJsonString = tempNode.toString();
            RG_OrderEntity rg_orderEntityWhitId = Tools.jsonConvertToEntity(orderNodeJsonString, RG_OrderEntity.class);
            RG_OrderEntity rg_orderEntity = (RG_OrderEntity) ordersDAOInstance.findAllById(rg_orderEntityWhitId).get(0);
            rg_orderEntitySet.add(rg_orderEntity);
//            Tools.executeSQLForUpdate(Info.MySQL, Info.APS, EntityConvertToSQL.insertSQLForAPS(rg_orderEntity));
        }
        rg_scheduleEntity.setOrders(rg_orderEntitySet);
        ordersDAOInstance.getTransaction().commit();
        //解析resources数据
        JsonNode resourcesNodes = rootNode.get("resources");
        Set<RG_ResourceEntity> rg_resourceEntitySet = new HashSet<>();
        ResourceDAOImpl resourceInstance = DAOFactory.getResourceInstance();
        for (JsonNode tempNode : resourcesNodes) {
            String resourcesNodesJsonString = tempNode.toString();
            RG_ResourceEntity rg_resourceEntityWhitId = Tools.jsonConvertToEntity(resourcesNodesJsonString, RG_ResourceEntity.class);
            RG_ResourceEntity rg_resourceEntity = (RG_ResourceEntity) resourceInstance.findAllById(rg_resourceEntityWhitId).get(0);
            rg_resourceEntitySet.add(rg_resourceEntity);
//            Tools.executeSQLForUpdate(Info.MySQL, Info.APS, EntityConvertToSQL.insertSQLForAPS(rg_resourceEntity));
        }
        rg_scheduleEntity.setResources(rg_resourceEntitySet);
        resourceInstance.getTransaction().commit();
        //解析groupResource数据
        JsonNode groupResourceNodes = rootNode.get("groupResource");
        Set<RG_GroupresourceEntity> rg_groupresourceEntitySet = new HashSet<>();
        GroupResourceDAOImpl groupResourceInstance = DAOFactory.getGroupResourceInstance();
        for (JsonNode tempNode : groupResourceNodes) {
            String groupResourceNodesJsonString = tempNode.toString();
            RG_GroupresourceEntity rg_groupresourceEntityWhitId = Tools.jsonConvertToEntity(groupResourceNodesJsonString, RG_GroupresourceEntity.class);
            RG_GroupresourceEntity rg_groupresourceEntity = (RG_GroupresourceEntity) groupResourceInstance.findAllById(rg_groupresourceEntityWhitId).get(0);
            rg_groupresourceEntitySet.add(rg_groupresourceEntity);
//            Tools.executeSQLForUpdate(Info.MySQL, Info.APS, EntityConvertToSQL.insertSQLForAPS(rg_groupresourceEntity));
        }
        rg_scheduleEntity.setGroups(rg_groupresourceEntitySet);
        groupResourceInstance.getTransaction().commit();
        //解析Site数据
        JsonNode siteNodes = rootNode.get("site");
        Set<RG_SiteEntity> rg_siteEntitySet = new HashSet<>();
        SiteDAOImpl siteInstance = DAOFactory.getSiteInstance();
        for (JsonNode tempNode : siteNodes) {
            String siteNodesJsonString = tempNode.toString();
            RG_SiteEntity rg_siteEntityWhitId = Tools.jsonConvertToEntity(siteNodesJsonString, RG_SiteEntity.class);
            RG_SiteEntity rg_siteEntity = (RG_SiteEntity) siteInstance.findAllById(rg_siteEntityWhitId).get(0);
            rg_siteEntitySet.add(rg_siteEntity);
//            Tools.executeSQLForUpdate(Info.MySQL, Info.APS, EntityConvertToSQL.insertSQLForAPS(rg_siteEntity));
        }
        rg_scheduleEntity.setSites(rg_siteEntitySet);
        siteInstance.getTransaction().commit();
        //提交保存
        ScheduleDAOImpl scheduleDAOImplInstance = DAOFactory.getScheduleDAOImplInstance();
        scheduleDAOImplInstance.save(rg_scheduleEntity);
        scheduleDAOImplInstance.getTransaction().commit();
    }
}