package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.OrdersDAO;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import com.rengu.util.SuperAction;
import com.rengu.util.Tools;
import org.hibernate.Transaction;

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

    public void getOrders() throws Exception {
        Transaction transaction = MySessionFactory.getSessionFactory().getCurrentSession().beginTransaction();
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list.get(0));

        httpServletResponse.setContentType("text/html");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(jsonString);
        printWriter.flush();
        printWriter.close();
    }
}