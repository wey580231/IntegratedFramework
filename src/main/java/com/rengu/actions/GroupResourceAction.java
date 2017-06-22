package com.rengu.actions;

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

    public String getAllGroupResource() throws Exception {
        GroupResourceDAO groupResourceInstance = DAOFactory.getGroupResourceInstance();
        List list = groupResourceInstance.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
        System.out.println(jsonString);
        return "success";
    }

    public void findAllByUsername() throws Exception {

    }

    public String save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_GroupresourceEntity rg_groupresourceEntity = Tools.jsonConvertToEntity(jsonString, RG_GroupresourceEntity.class);
        GroupResourceDAOImpl groupResourceDAOInstance = DAOFactory.getGroupResourceInstance();
        if (groupResourceDAOInstance.save(rg_groupresourceEntity)) {
            return "success";
        } else {
            WebSocketNotification.sendMessage("保存失败", "");
            return "success";
        }
    }

    public String delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_GroupresourceEntity rg_groupresourceEntity = Tools.jsonConvertToEntity(jsonString, RG_GroupresourceEntity.class);
        GroupResourceDAOImpl groupResourceDAOInstance = DAOFactory.getGroupResourceInstance();
        if (groupResourceDAOInstance.delete(rg_groupresourceEntity)) {
            return "success";
        } else {
            WebSocketNotification.sendMessage("删除失败", "");
            return "success";
        }
    }

    public String update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_GroupresourceEntity rg_groupresourceEntity = Tools.jsonConvertToEntity(jsonString, RG_GroupresourceEntity.class);
        GroupResourceDAOImpl groupResourceDAOInstance = DAOFactory.getGroupResourceInstance();
        if (groupResourceDAOInstance.update(rg_groupresourceEntity)) {
            return "success";
        } else {
            WebSocketNotification.sendMessage("更新失败", "");
            return "success";
        }

    }
}
