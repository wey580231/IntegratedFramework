package com.rengu.actions;

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
        System.out.println(jsonString);
    }

    public void findAllByUsername() throws Exception {

    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ShiftEntity rg_shiftEntity = Tools.jsonConvertToEntity(jsonString, RG_ShiftEntity.class);
        ShiftDAOImpl shiftDAOInstance = DAOFactory.getShiftInstance();
        if (shiftDAOInstance.save(rg_shiftEntity)) {
        } else {
            WebSocketNotification.sendMessage("保存失败", "username");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ShiftEntity rg_shiftEntity = Tools.jsonConvertToEntity(jsonString, RG_ShiftEntity.class);
        ShiftDAOImpl shiftDAOInstance = DAOFactory.getShiftInstance();
        if (shiftDAOInstance.delete(rg_shiftEntity)) {
        } else {
            WebSocketNotification.sendMessage("删除失败", "username");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_ShiftEntity rg_shiftEntity = Tools.jsonConvertToEntity(jsonString, RG_ShiftEntity.class);
        ShiftDAOImpl shiftDAOInstance = DAOFactory.getShiftInstance();
        if (shiftDAOInstance.update(rg_shiftEntity)) {
        } else {
            WebSocketNotification.sendMessage("更新失败", "username");
        }

    }
}
