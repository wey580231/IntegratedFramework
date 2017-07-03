package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.ProcessDAOImpl;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ProcessEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/26.
 */
public class ProcessAction extends SuperAction {
    public void getAllByIsRootNode() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        boolean isRootNode = jsonNode.get("isRootNode").asBoolean();
        ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
        List<RG_ProcessEntity> rg_processEntityList = processDAO.findAllByIsRootNode(isRootNode);
        String jsonString = Tools.entityConvertToJsonString(rg_processEntityList);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void getAllById() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        String processId = jsonNode.get("id").asText();
        ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
        RG_ProcessEntity rg_processEntity = processDAO.findAllById(processId);
        String jsonString = Tools.entityConvertToJsonString(rg_processEntity);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ProcessEntity rg_processEntity = Tools.jsonConvertToEntity(jsonString, RG_ProcessEntity.class);
        rg_processEntity.setId(Tools.getUUID());
        ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
        if (processDAO.save(rg_processEntity)) {
        } else {
            System.out.println("保存失败");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ProcessEntity rg_processEntity = Tools.jsonConvertToEntity(jsonString, RG_ProcessEntity.class);
        ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
        if (processDAO.delete(rg_processEntity)) {
        } else {
            System.out.println("删除失败");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
        if (processDAO.update(rg_orderEntity)) {
        } else {
            System.out.println("更新失败");
        }
    }
}
