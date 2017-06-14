package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.*;
import com.rengu.entity.*;
import com.rengu.util.ApsTools;
import com.rengu.util.DAOFactory;
import com.rengu.util.DatabaseInfo;
import com.rengu.util.Tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class ScheduleAction extends SuperAction {

    public void beginSchedule() throws Exception {

        //初始化数据库表
        String[] tableList = {DatabaseInfo.APS_ORDER, DatabaseInfo.APS_RESOURCE, DatabaseInfo.APS_GROUPRESOURCE, DatabaseInfo.APS_SITE};
        Tools.executeSQLForInitTable(DatabaseInfo.MySQL, DatabaseInfo.APS, tableList);
        //更新数据库表内容
        String jsonString = Tools.getHttpRequestBody(this.httpServletRequest);
        JsonNode rootNode = Tools.jsonTreeModelParse(jsonString);
        RG_ScheduleEntity rg_scheduleEntity = new RG_ScheduleEntity();
        //解析排程名称
        JsonNode nameNodes = rootNode.get("name");
        rg_scheduleEntity.setName(nameNodes.asText());
        rg_scheduleEntity.setState(RG_ScheduleEntity.APS_COMPUTE);

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
//            Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertAPSConfigSQL(APS_ConfigNodeKey, APS_ConfigNodeValue));
        }
        //解析Layout数据
        JsonNode layoutNode = rootNode.get("layout");
        RG_LayoutEntity rg_layoutEntityWithId = Tools.jsonConvertToEntity(layoutNode.toString(), RG_LayoutEntity.class);
        LayoutDAOImpl layoutDAO = DAOFactory.getLayoutDAOImplInstance();
        RG_LayoutEntity rg_layoutEntity = layoutDAO.findAllById(rg_layoutEntityWithId.getId());
        rg_scheduleEntity.setLayout(rg_layoutEntity);
        layoutDAO.getTransaction().commit();
        //解析订单数据
        JsonNode orderNodes = rootNode.get("orders");
        Set<RG_OrderEntity> rg_orderEntitySet = new HashSet<>();
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        for (JsonNode tempNode : orderNodes) {
            String orderNodeJsonString = tempNode.toString();
            RG_OrderEntity rg_orderEntityWhitId = Tools.jsonConvertToEntity(orderNodeJsonString, RG_OrderEntity.class);
            RG_OrderEntity rg_orderEntity = ordersDAOInstance.findAllById(rg_orderEntityWhitId.getId());
            rg_orderEntitySet.add(rg_orderEntity);
//            Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_orderEntity));
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
            RG_ResourceEntity rg_resourceEntity = resourceInstance.findAllById(rg_resourceEntityWhitId.getId());
            rg_resourceEntitySet.add(rg_resourceEntity);
//            Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_resourceEntity));
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
            RG_GroupresourceEntity rg_groupresourceEntity = groupResourceInstance.findAllById(rg_groupresourceEntityWhitId.getId());
            rg_groupresourceEntitySet.add(rg_groupresourceEntity);
//            Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_groupresourceEntity));
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
            RG_SiteEntity rg_siteEntity = siteInstance.findAllById(rg_siteEntityWhitId.getId());
            rg_siteEntitySet.add(rg_siteEntity);
//            Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_siteEntity));
        }
        rg_scheduleEntity.setSites(rg_siteEntitySet);
        siteInstance.getTransaction().commit();

        //APS ID计算标识
        String apsId = String.valueOf(date.getTime());
        rg_scheduleEntity.setApsFlag(apsId);

        //提交保存
        ScheduleDAOImpl scheduleDAOImplInstance = DAOFactory.getScheduleDAOImplInstance();
        scheduleDAOImplInstance.save(rg_scheduleEntity);
        scheduleDAOImplInstance.getTransaction().commit();

        //调用排程接口
        String executeCmd = "/NCL:RUN?Program=./Model/Script/ScriptAutoScheduling.n&REPLY=127.0.0.1:8080/aps/updateProgress&ID=" + apsId + "&DELAY=1000000&buffer=001";
        int result = ApsTools.instance().executeCommand(executeCmd);

        //更新本地plan数据库
        if (result != -1) {
            String SQLString = "select * from aps_plan";
            List<?> list = Tools.executeSQLForResultSet(DatabaseInfo.MySQL, DatabaseInfo.APS, SQLString);
            for (Object object : list) {
                if (object instanceof HashMap) {
                    RG_PlanEntity rg_planEntity = new RG_PlanEntity();
                    Map tempMap = (HashMap) object;
                    rg_planEntity.setIdTask(tempMap.get("idTask").toString());
                    rg_planEntity.setIdJob(tempMap.get("idJob").toString());
                    rg_planEntity.setNameTask(tempMap.get("nameTask").toString());
                    rg_planEntity.setNameOrder(tempMap.get("nameOrder").toString());
                    rg_planEntity.setNameJob(tempMap.get("nameJob").toString());
                    rg_planEntity.setNameResource(tempMap.get("nameResource").toString());
                    rg_planEntity.setNameGroupResource(tempMap.get("nameGroupResource").toString());
                    rg_planEntity.setNameTypeResource(tempMap.get("nameTypeResource").toString());
                    rg_planEntity.setNameSite(tempMap.get("nameSite").toString());
                    rg_planEntity.setNameProvider(tempMap.get("nameProvider").toString());
                    rg_planEntity.setOrdToParentTask(Short.parseShort(tempMap.get("ordToParentTask").toString()));
                    rg_planEntity.setIdTaskResourceSucc(tempMap.get("idTaskResourceSucc").toString());
                    rg_planEntity.setPreemptiveTask(tempMap.get("preemptiveTask").toString());
                    rg_planEntity.setDivisibleTask(tempMap.get("divisibleTask").toString());
                    rg_planEntity.setContinuousTask(tempMap.get("continuousTask").toString());
                    rg_planEntity.setQuantityTask(Short.parseShort(tempMap.get("quantityTask").toString()));
                    rg_planEntity.setQuantityResourceTask(Short.parseShort(tempMap.get("quantityResourceTask").toString()));
                    rg_planEntity.setQuantityBatchTask(Short.parseShort(tempMap.get("quantityBatchTask").toString()));
                    rg_planEntity.setQtySequence(Short.parseShort(tempMap.get("qtySequence").toString()));
                    rg_planEntity.setT1Task(tempMap.get("t1Task").toString());
                    rg_planEntity.setT2Task(tempMap.get("t2Task").toString());
                    rg_planEntity.setT2ExtendedTask(tempMap.get("t2ExtendedTask").toString());
                    rg_planEntity.setAdvice(tempMap.get("advice").toString());
                    rg_planEntity.setEstimateTask(tempMap.get("estimateTask").toString());
                    rg_planEntity.setTimeTask(tempMap.get("timeTask").toString());
                    rg_planEntity.setInitTimeTask(tempMap.get("initTimeTask").toString());
                    rg_planEntity.setUnitTimeTask(tempMap.get("unitTimeTask").toString());
                    rg_planEntity.setPostTimeTask(tempMap.get("postTimeTask").toString());
                    rg_planEntity.setCheckTimeTask(tempMap.get("checkTimeTask").toString());
                    rg_planEntity.setIdGroupResource0Task(tempMap.get("idGroupResource0Task").toString());
                    rg_planEntity.setIdResource0Task(tempMap.get("idResource0Task").toString());
                    rg_planEntity.setIdSite0Task(tempMap.get("idSite0Task").toString());
                    rg_planEntity.setQuantity0Task(Short.parseShort(tempMap.get("quantity0Task").toString()));
                    rg_planEntity.setT10Task(tempMap.get("t10Task").toString());
                    rg_planEntity.setT20Task(tempMap.get("t20Task").toString());
                    rg_planEntity.setT20ExtendedTask(tempMap.get("t20ExtendedTask").toString());
                    rg_planEntity.setT1Job(tempMap.get("t1Job").toString());
                    rg_planEntity.setT2Job(tempMap.get("t2Job").toString());
                    rg_planEntity.setQuantityJob(Short.parseShort(tempMap.get("quantityJob").toString()));
                    rg_planEntity.setNbTaskJob(Short.parseShort(tempMap.get("nbTaskJob").toString()));
                    rg_planEntity.setRefProductJob(tempMap.get("refProductJob").toString());
                    rg_planEntity.setOrdToRootJob(Short.parseShort(tempMap.get("ordToRootJob").toString()));
                    rg_planEntity.setOrdToRootChildJob(tempMap.get("OrdToRootChildJob").toString());
                    rg_planEntity.setT1Order(tempMap.get("t1Order").toString());
                    rg_planEntity.setT2Order(tempMap.get("t2Order").toString());
                    rg_planEntity.setQuantityOrder(Short.parseShort(tempMap.get("quantityOrder").toString()));
                    rg_planEntity.setPriorityOrder(Short.parseShort(tempMap.get("priorityOrder").toString()));
                    rg_planEntity.setColorOrder(tempMap.get("colorOrder").toString());
                    rg_planEntity.setState(Byte.parseByte(tempMap.get("state").toString()));
//                //获取Club实体
//                ClubDAOImpl clubDAO = DAOFactory.getClubDAOImplInstance();
//                rg_planEntity.setClubByIdClub(clubDAO.findAllById(tempMap.get("idClub").toString()));
//                clubDAO.getTransaction().commit();
//                //获取Process实体
//                ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
//                rg_planEntity.setProcessByIdProcess(processDAO.findAllById(tempMap.get("idProcess").toString()));
//                processDAO.getTransaction().commit();
//                //获取Order实体
//                OrdersDAOImpl ordersDAO = DAOFactory.getOrdersDAOInstance();
//                rg_planEntity.setOrderByIdOrder(ordersDAO.findAllById(tempMap.get("idOrder").toString()));
//                ordersDAO.getTransaction().commit();
//                //获取Resource实体
//                ResourceDAOImpl resourceDAO = DAOFactory.getResourceInstance();
//                rg_planEntity.setResourceByIdResource(resourceDAO.findAllById(tempMap.get("idResource").toString()));
//                resourceDAO.getTransaction().commit();
//                //获取Site实体
//                SiteDAOImpl siteDAO = DAOFactory.getSiteInstance();
//                rg_planEntity.setSiteByIdSite(siteDAO.findAllById(tempMap.get("idSite").toString()));
//                siteDAO.getTransaction().commit();
//                //获取GroupResource实体
//                GroupResourceDAOImpl groupResourceDAO = DAOFactory.getGroupResourceInstance();
//                rg_planEntity.setGroupresourceByIdGroupResource(groupResourceDAO.findAllById(tempMap.get("idGroupResource").toString()));
//                groupResourceDAO.getTransaction().commit();
//                //获取TypeResource实体
//                TyperescourceDAOImpl typerescourceDAO = DAOFactory.getTyperescourceInstance();
//                rg_planEntity.setGroupresourceByIdGroupResource(groupResourceDAO.findAllById(tempMap.get("idTypeResource").toString()));
//                typerescourceDAO.getTransaction().commit();
//                //获取Provider实体
//                ProviderDAOImpl providerDAO = DAOFactory.getProviderDAOImplInstance();
//                rg_planEntity.setProviderByIdProvider(providerDAO.findAllById(tempMap.get("idProvider").toString()));
//                providerDAO.getTransaction().commit();
                }
            }
        }
    }
}