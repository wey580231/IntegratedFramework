package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.OrdersDAO;
import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/24.
 */
public class OrdersAction extends SuperAction implements ModelDriven<RG_OrderEntity> {
    RG_OrderEntity rg_orderEntity = new RG_OrderEntity();

    @Override
    public RG_OrderEntity getModel() {
        return this.rg_orderEntity;
    }

    public void getAllOrders() throws Exception {
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void findAllByUsername() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAllByUsername(rg_orderEntity);
        Tools.jsonPrint(Tools.entityConvertToJsonString(list), this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.save(rg_orderEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("保存失败", rg_orderEntity.getClubByIdClub().getName());
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.delete(rg_orderEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("删除失败", rg_orderEntity.getClubByIdClub().getName());
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.update(rg_orderEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("更新失败", rg_orderEntity.getClubByIdClub().getName());
        }

    }
}
