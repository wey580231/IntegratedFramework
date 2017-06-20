package com.rengu.actions;

import com.rengu.DAO.OrdersDAO;
import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/24.
 */
public class OrdersAction extends SuperAction {
    public void getAllOrders() throws Exception {
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
        System.out.println(jsonString);
    }

    public void findAllByUsername() throws Exception {

    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.save(rg_orderEntity)) {
        } else {
            System.out.println("保存失败");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.delete(rg_orderEntity)) {
        } else {
            System.out.println("删除失败");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.update(rg_orderEntity)) {
        } else {
            System.out.println("更新失败");
        }

    }
}
