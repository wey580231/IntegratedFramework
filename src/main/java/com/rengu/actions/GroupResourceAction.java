package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.GroupResourceDAO;
import com.rengu.DAO.impl.GroupResourceDAOImpl;
import com.rengu.entity.RG_GroupresourceEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class GroupResourceAction extends SuperAction implements ModelDriven<RG_GroupresourceEntity> {
    RG_GroupresourceEntity rg_groupresourceEntity = new RG_GroupresourceEntity();

    @Override
    public RG_GroupresourceEntity getModel() {
        return this.rg_groupresourceEntity;
    }

    public void getAllGroupResource() throws Exception {
        GroupResourceDAO groupResourceInstance = DAOFactory.getGroupResourceInstance();
        List list = groupResourceInstance.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void findAllByUsername() throws Exception {

    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_GroupresourceEntity rg_groupresourceEntity = Tools.jsonConvertToEntity(jsonString, RG_GroupresourceEntity.class);
        rg_groupresourceEntity.setId(Tools.getUUID());
        GroupResourceDAOImpl groupResourceDAOInstance = DAOFactory.getGroupResourceInstance();
        if (groupResourceDAOInstance.save(rg_groupresourceEntity)) {
            Tools.jsonPrint(Tools.resultCode("ok", "Operate success"), this.httpServletResponse);
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("Groupresource保存失败", "alert"));
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_GroupresourceEntity rg_groupresourceEntity = Tools.jsonConvertToEntity(jsonString, RG_GroupresourceEntity.class);
        GroupResourceDAOImpl groupResourceDAOInstance = DAOFactory.getGroupResourceInstance();
        if (groupResourceDAOInstance.delete(rg_groupresourceEntity)) {
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("Groupresource删除失败", "alert"));
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_GroupresourceEntity rg_groupresourceEntity = Tools.jsonConvertToEntity(jsonString, RG_GroupresourceEntity.class);
        GroupResourceDAOImpl groupResourceDAOInstance = DAOFactory.getGroupResourceInstance();
        if (groupResourceDAOInstance.update(rg_groupresourceEntity)) {
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("Groupresource更新失败", "alert"));
        }

    }

    public void findAllById() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        String groupResourceId = jsonNode.get("id").asText();
        GroupResourceDAOImpl groupResourceDAO = DAOFactory.getGroupResourceInstance();
        RG_GroupresourceEntity rg_groupresourceEntity = groupResourceDAO.findAllById(groupResourceId);
        String resultString = Tools.entityConvertToJsonString(rg_groupresourceEntity);
        Tools.jsonPrint(resultString, this.httpServletResponse);
    }
}
