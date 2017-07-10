package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.ShiftDAO;
import com.rengu.DAO.impl.ShiftDAOImpl;
import com.rengu.entity.RG_ShiftEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class ShiftAction extends SuperAction implements ModelDriven<RG_ShiftEntity> {
    RG_ShiftEntity rg_shiftEntity = new RG_ShiftEntity();

    @Override
    public RG_ShiftEntity getModel() {
        return this.rg_shiftEntity;
    }

    public void getAllShift() throws Exception {
        ShiftDAO shiftDAO = DAOFactory.getShiftInstance();
        List list = shiftDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ShiftEntity rg_shiftEntity = Tools.jsonConvertToEntity(jsonString, RG_ShiftEntity.class);
        rg_shiftEntity.setId(Tools.getUUID());
        ShiftDAOImpl shiftDAOInstance = DAOFactory.getShiftInstance();
        if (shiftDAOInstance.save(rg_shiftEntity)) {
        } else {
            System.out.println("保存失败");
            WebSocketNotification.broadcast("保存失败");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ShiftEntity rg_shiftEntity = Tools.jsonConvertToEntity(jsonString, RG_ShiftEntity.class);
        ShiftDAOImpl shiftDAOInstance = DAOFactory.getShiftInstance();
        if (shiftDAOInstance.delete(rg_shiftEntity)) {
        } else {
            System.out.println("删除失败");
            WebSocketNotification.broadcast("删除失败");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ShiftEntity rg_shiftEntity = Tools.jsonConvertToEntity(jsonString, RG_ShiftEntity.class);
        ShiftDAOImpl shiftDAOInstance = DAOFactory.getShiftInstance();
        if (shiftDAOInstance.update(rg_shiftEntity)) {
        } else {
            System.out.println("更新失败");
            WebSocketNotification.broadcast("更新失败");
        }

    }

    public void findAllById() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        String shiftId = jsonNode.get("id").asText();
        ShiftDAOImpl shiftDAO = DAOFactory.getShiftInstance();
        RG_ShiftEntity rg_shiftEntity = shiftDAO.findAllById(shiftId);
        String resultString = Tools.entityConvertToJsonString(rg_shiftEntity);
        Tools.jsonPrint(resultString, this.httpServletResponse);
    }
}
