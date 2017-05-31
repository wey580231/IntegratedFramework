package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.OrdersDAO;
import com.rengu.DAO.impl.OrdersDAOImpl;
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

    public void getAllOrders() throws Exception {
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void findAllByUsername() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAllByUsername(rg_typerescourceEntity);
        Tools.jsonPrint(Tools.entityConvertToJsonString(list), this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.save(rg_typerescourceEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("保存失败", "username");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.delete(rg_typerescourceEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("删除失败", "username");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_TyperescourceEntity rg_typerescourceEntity = Tools.jsonConvertToEntity(jsonString, RG_TyperescourceEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.update(rg_typerescourceEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("更新失败", "username");
        }

    }
}
