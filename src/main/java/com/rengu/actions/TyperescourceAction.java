package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.TyperescourceDAO;
import com.rengu.DAO.impl.TyperescourceDAOImpl;
import com.rengu.entity.RG_TyperescourceEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class TyperescourceAction extends SuperAction implements ModelDriven<RG_TyperescourceEntity> {
    RG_TyperescourceEntity rg_typerescourceEntity = new RG_TyperescourceEntity();

    @Override
    public RG_TyperescourceEntity getModel() {
        return this.rg_typerescourceEntity;
    }

    public void getAllTypeRescource() throws Exception {
        TyperescourceDAO typerescourceDAO = DAOFactory.getTyperescourceInstance();
        List list = typerescourceDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
        System.out.println(jsonString);
    }

    public void findAllByUsername() throws Exception {

    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        rg_typerescourceEntity.setId(Tools.getUUID());
        TyperescourceDAOImpl typerescourceDAOInstance = DAOFactory.getTyperescourceInstance();
        if (typerescourceDAOInstance.save(rg_typerescourceEntity)) {
        } else {
            WebSocketNotification.sendMessage("保存失败", "username");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        TyperescourceDAOImpl typerescourceDAOInstance = DAOFactory.getTyperescourceInstance();
        if (typerescourceDAOInstance.delete(rg_typerescourceEntity)) {
        } else {
            WebSocketNotification.sendMessage("删除失败", "username");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        TyperescourceDAOImpl typerescourceDAOInstance = DAOFactory.getTyperescourceInstance();
        if (typerescourceDAOInstance.update(rg_typerescourceEntity)) {
        } else {
            WebSocketNotification.sendMessage("更新失败", "username");
        }

    }
}
