package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.OrdersDAO;
import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.entity.RG_SiteEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class SiteAction extends SuperAction implements ModelDriven<RG_SiteEntity> {
    RG_SiteEntity rg_siteEntity = new RG_SiteEntity();

    @Override
    public RG_SiteEntity getModel() {
        return this.rg_siteEntity;
    }

    public void getAllOrders() throws Exception {
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void findAllByUsername() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        OrdersDAO ordersDAO = DAOFactory.getOrdersDAOInstance();
        List list = ordersDAO.findAllByUsername(rg_siteEntity);
        Tools.jsonPrint(Tools.entityConvertToJsonString(list), this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.save(rg_siteEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("保存失败", "username");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.delete(rg_siteEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("删除失败", "username");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        OrdersDAOImpl ordersDAOInstance = DAOFactory.getOrdersDAOInstance();
        if (ordersDAOInstance.update(rg_siteEntity)) {
            ordersDAOInstance.getTransaction().commit();
        } else {
            WebSocketNotification.sendMessage("更新失败", "username");
        }

    }
}
