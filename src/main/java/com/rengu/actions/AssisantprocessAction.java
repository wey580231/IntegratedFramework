package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.AssisantprocessDAO;
import com.rengu.DAO.impl.AssisantprocessDAOImpl;
import com.rengu.entity.RG_AssisantprocessEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class AssisantprocessAction extends SuperAction implements ModelDriven<RG_AssisantprocessEntity> {
    RG_AssisantprocessEntity rg_assisantprocessEntity = new RG_AssisantprocessEntity();

    @Override
    public RG_AssisantprocessEntity getModel() {
        return this.rg_assisantprocessEntity;
    }

    public void getAllAssisantProcess() throws Exception {
        AssisantprocessDAO assisantprocessDAO = DAOFactory.getAssisantprocessDAOInstance();
        List list = assisantprocessDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void findAllByUsername() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_AssisantprocessEntity rg_assisantprocessEntity = Tools.jsonConvertToEntity(jsonString, RG_AssisantprocessEntity.class);
        AssisantprocessDAO assisantprocessDAO = DAOFactory.getAssisantprocessDAOInstance();
        List list = assisantprocessDAO.findAllByUsername(rg_assisantprocessEntity);
        Tools.jsonPrint(Tools.entityConvertToJsonString(list), this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_AssisantprocessEntity rg_assisantprocessEntity = Tools.jsonConvertToEntity(jsonString, RG_AssisantprocessEntity.class);
        AssisantprocessDAOImpl assisantprocessDAOInstance = DAOFactory.getAssisantprocessDAOInstance();
        if (assisantprocessDAOInstance.save(rg_assisantprocessEntity)) {
            assisantprocessDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("保存失败", "username");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_AssisantprocessEntity rg_assisantprocessEntity = Tools.jsonConvertToEntity(jsonString, RG_AssisantprocessEntity.class);
        AssisantprocessDAOImpl assisantprocessDAOInstance = DAOFactory.getAssisantprocessDAOInstance();
        if (assisantprocessDAOInstance.delete(rg_assisantprocessEntity)) {
            assisantprocessDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("删除失败", "username");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_AssisantprocessEntity rg_assisantprocessEntity = Tools.jsonConvertToEntity(jsonString, RG_AssisantprocessEntity.class);
        AssisantprocessDAOImpl assisantprocessDAOInstance = DAOFactory.getAssisantprocessDAOInstance();
        if (assisantprocessDAOInstance.update(rg_assisantprocessEntity)) {
            assisantprocessDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("更新失败", "username");
        }

    }
}
