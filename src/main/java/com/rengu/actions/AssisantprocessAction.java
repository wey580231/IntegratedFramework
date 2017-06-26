package com.rengu.actions;

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
public class AssisantprocessAction extends SuperAction {

    public void getAllAssisantProcess() throws Exception {
        AssisantprocessDAO assisantprocessDAO = DAOFactory.getAssisantprocessDAOInstance();
        List list = assisantprocessDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
        System.out.println(jsonString);
    }

    public void findAllByUsername() throws Exception {

    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_AssisantprocessEntity rg_assisantprocessEntity = Tools.jsonConvertToEntity(jsonString, RG_AssisantprocessEntity.class);
        rg_assisantprocessEntity.setId(Tools.getUUID());
        AssisantprocessDAOImpl assisantprocessDAOInstance = DAOFactory.getAssisantprocessDAOInstance();
        if (assisantprocessDAOInstance.save(rg_assisantprocessEntity)) {
        } else {
            WebSocketNotification.sendMessage("保存失败", "username");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_AssisantprocessEntity rg_assisantprocessEntity = Tools.jsonConvertToEntity(jsonString, RG_AssisantprocessEntity.class);
        AssisantprocessDAOImpl assisantprocessDAOInstance = DAOFactory.getAssisantprocessDAOInstance();
        if (assisantprocessDAOInstance.delete(rg_assisantprocessEntity)) {
        } else {
            WebSocketNotification.sendMessage("删除失败", "username");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_AssisantprocessEntity rg_assisantprocessEntity = Tools.jsonConvertToEntity(jsonString, RG_AssisantprocessEntity.class);
        AssisantprocessDAOImpl assisantprocessDAOInstance = DAOFactory.getAssisantprocessDAOInstance();
        if (assisantprocessDAOInstance.update(rg_assisantprocessEntity)) {
        } else {
            WebSocketNotification.sendMessage("更新失败", "username");
        }
    }
}
