package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.AdjustDeviceDAOImpl;
import com.rengu.entity.RG_AdjustDeviceEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.DatabaseInfo;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wey580231 on 2017/6/19.
 */
public class AdjustDeviceAction extends SuperAction {

    public void getAllAdjustDeviceException() throws Exception {
        AdjustDeviceDAOImpl adjustProcessDAO = DAOFactory.getAdjustDeviceDAOImplInstance();
        List<RG_AdjustDeviceEntity> adjustDeviceEntityList = adjustProcessDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(adjustDeviceEntityList);
        Tools.jsonPrint(jsonString, httpServletResponse);
    }

    //撤销资源
    public void cancelResource() throws Exception {

        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        String id = jsonNode.get("id").asText();

        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        //transaction.begin();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }

        //String idResource = session.createQuery("");

        //查找所有资源
        String SQLString = "select * from " + DatabaseInfo.APS_RESOURCE + "where STATE = 1";
        List resourceList = Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, SQLString);

        //1.先判断撤销的资源是否是单个
        //遍历APS资源表
        for(Object object : resourceList){
            if (object instanceof HashMap) {
                Map rowData = (HashMap) object;

                if (rowData.get("IDTYPERESOURCE") != null) {
                    String idTypeResource = rowData.get("IDTYPERESOURCE").toString();

                    //查找所有类型为idTypeResource的资源的数量
                    String SQLStringEXC = "select * from " + DatabaseInfo.APS_RESOURCE + "where STATE = 1 and IDTYPERESOURCE = '" + idTypeResource +"'";
                    List resourceListEXC = Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, SQLStringEXC);

                    if(resourceListEXC.size() == 1){ //单个资源


                    }else if(resourceListEXC.size() > 1){ //不是单个

                    }

                }
            }

        }

        //2.调用撤销资源接口rejactresource

        //3.如果撤销的资源是单个资源，那么什么都不做，不调继续优化接口reusumeScheduling （此时资源不够，无法计算）

        //4.如果撤销的资源不是单个资源，那么先判断是否有候选订单（order表中state=0的订单为候选订单）；如果有候选订单，则调继续优化接口reusumeScheduling，否则什么都不做。


    }

    //恢复资源
    public void resumeResource() throws Exception {

    }
}
