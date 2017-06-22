package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.SiteDAO;
import com.rengu.DAO.impl.SiteDAOImpl;
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

    public String getAllSite() throws Exception {
        SiteDAO siteDAO = DAOFactory.getSiteInstance();
        List list = siteDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
        System.out.println(jsonString);
        return "success";
    }

    public void findAllByUsername() throws Exception {

    }

    public String save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        SiteDAOImpl siteDAOInstance = DAOFactory.getSiteInstance();
        if (siteDAOInstance.save(rg_siteEntity)) {
            return "success";
        } else {
            WebSocketNotification.sendMessage("保存失败", "username");
            return "success";
        }
    }

    public String delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        SiteDAOImpl siteDAOInstance = DAOFactory.getSiteInstance();
        if (siteDAOInstance.delete(rg_siteEntity)) {
            return "success";
        } else {
            WebSocketNotification.sendMessage("删除失败", "username");
            return "success";
        }
    }

    public String update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        SiteDAOImpl siteDAOInstance = DAOFactory.getSiteInstance();
        if (siteDAOInstance.update(rg_siteEntity)) {
            return "success";
        } else {
            WebSocketNotification.sendMessage("更新失败", "username");
            return "success";
        }

    }
}
