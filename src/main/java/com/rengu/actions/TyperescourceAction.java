package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
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
            System.out.println("保存失败");
            WebSocketNotification.broadcast("保存失败");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        TyperescourceDAOImpl typerescourceDAOInstance = DAOFactory.getTyperescourceInstance();
        if (typerescourceDAOInstance.delete(rg_typerescourceEntity)) {
        } else {
            System.out.println("删除失败");
            WebSocketNotification.broadcast("删除失败");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        TyperescourceDAOImpl typerescourceDAOInstance = DAOFactory.getTyperescourceInstance();
        if (typerescourceDAOInstance.update(rg_typerescourceEntity)) {
        } else {
            System.out.println("更新失败");
            WebSocketNotification.broadcast("更新失败");
        }

    }

    public void findAllById() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        String typeRescourceId = jsonNode.get("id").asText();
        TyperescourceDAOImpl typerescourceDAO = DAOFactory.getTyperescourceInstance();
        RG_TyperescourceEntity rg_typerescourceEntity = typerescourceDAO.findAllById(typeRescourceId);
        String resultString = Tools.entityConvertToJsonString(rg_typerescourceEntity);
        Tools.jsonPrint(resultString, this.httpServletResponse);
    }
}
