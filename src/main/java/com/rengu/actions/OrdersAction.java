package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.OrdersDAO;
import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.ExceptionCheck;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.Date;
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
    }

    public void findAllById() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        String orderId = jsonNode.get("id").asText();
        OrdersDAOImpl ordersDAO = DAOFactory.getOrdersDAOInstance();
        RG_OrderEntity rg_orderEntity = ordersDAO.findAllById(orderId);
        String resultString = Tools.entityConvertToJsonString(rg_orderEntity);
        Tools.jsonPrint(resultString, this.httpServletResponse);
    }

    public void findAllByisFinishedAndDate() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        Date startTime = Tools.stringConvertToDate(jsonNode.get("startTime").asLong());
        Date endTime = Tools.stringConvertToDate(jsonNode.get("endTime").asLong());
        boolean isFinished = jsonNode.get("isFinished").asBoolean();
        OrdersDAOImpl ordersDAO = DAOFactory.getOrdersDAOInstance();
        List<RG_OrderEntity> rg_orderEntityList = ordersDAO.findAllByisFinishedAndDate(startTime, endTime, isFinished);
        String resultString = Tools.entityConvertToJsonString(rg_orderEntityList);
        Tools.jsonPrint(resultString, this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        rg_orderEntity.setId(Tools.getUUID());

        rg_orderEntity.setOrigin("0");//设置订单为手动输入
        rg_orderEntity.setState(Byte.parseByte("0"));//设置订单状态为计划
        rg_orderEntity.setT1(rg_orderEntity.getT0());
        rg_orderEntity.setEstimate(Short.parseShort("4246"));
        rg_orderEntity.setDelay(Short.parseShort("0"));
        rg_orderEntity.setColor("79 129 189");
        rg_orderEntity.setFinished(false);
        rg_orderEntity.setIdGroupResource("G01");
        rg_orderEntity.setProductByIdProduct(DAOFactory.getProductDAOImplInstance().findAllById("Kqd"));
        rg_orderEntity.setClubByIdClub(DAOFactory.getClubDAOImplInstance().findAllById("001"));

        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        boolean isSaved = ordersDAOInstance.save(rg_orderEntity);
        if (isSaved) {
            ExceptionCheck.orderExceptionCheck(rg_orderEntity);
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("Order保存失败", "alert"));
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.delete(rg_orderEntity)) {
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("Order删除失败", "alert"));
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.update(rg_orderEntity)) {
            ExceptionCheck.orderExceptionCheck(rg_orderEntity);
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("Order更新失败", "alert"));
        }
    }
}
