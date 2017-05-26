package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.OrdersDAO;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.SuperAction;
import com.rengu.util.Tools;

import java.io.IOException;
import java.io.PrintWriter;
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
        httpPrint(jsonString);
    }

    public void findAllByUsername() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(jsonString, RG_OrderEntity.class);
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAllByUsername(rg_orderEntity);
        httpPrint(Tools.entityConvertToJsonString(list));
    }

    private void httpPrint(String string) {
        httpServletResponse.setContentType("text/html");
        PrintWriter printWriter = null;
        try {
            printWriter = httpServletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println(string);
        printWriter.flush();
        printWriter.close();
    }
}
