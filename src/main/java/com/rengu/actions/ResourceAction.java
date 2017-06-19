package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.ResourceDAO;
import com.rengu.DAO.impl.ResourceDAOImpl;
import com.rengu.entity.RG_ResourceEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class ResourceAction extends SuperAction implements ModelDriven<RG_ResourceEntity> {
    RG_ResourceEntity rg_resourceEntity = new RG_ResourceEntity();

    @Override
    public RG_ResourceEntity getModel() {
        return this.rg_resourceEntity;
    }

    public void getAllResource() throws Exception {
        ResourceDAO resourceDAO = DAOFactory.getResourceInstance();
        List list = resourceDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
        System.out.println(jsonString);
    }

    public void findAllByUsername() throws Exception {

    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ResourceEntity rg_resourceEntity = Tools.jsonConvertToEntity(jsonString, RG_ResourceEntity.class);
        ResourceDAOImpl resourceDAOInstance = DAOFactory.getResourceInstance();
        if (resourceDAOInstance.save(rg_resourceEntity)) {
            resourceDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("保存失败", rg_resourceEntity.getClubByIdClub().getName());
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ResourceEntity rg_resourceEntity = Tools.jsonConvertToEntity(jsonString, RG_ResourceEntity.class);
        ResourceDAOImpl resourceDAOInstance = DAOFactory.getResourceInstance();
        if (resourceDAOInstance.delete(rg_resourceEntity)) {
            resourceDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("删除失败", rg_resourceEntity.getClubByIdClub().getName());
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ResourceEntity rg_resourceEntity = Tools.jsonConvertToEntity(jsonString, RG_ResourceEntity.class);
        ResourceDAOImpl resourceDAOInstance = DAOFactory.getResourceInstance();
        if (resourceDAOInstance.update(rg_resourceEntity)) {
            resourceDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("更新失败", rg_resourceEntity.getClubByIdClub().getName());
        }

    }
}
