package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
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

    public void getAllSite() throws Exception {
        SiteDAO siteDAO = DAOFactory.getSiteInstance();
        List list = siteDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void save() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        rg_siteEntity.setId(Tools.getUUID());
        SiteDAOImpl siteDAOInstance = DAOFactory.getSiteInstance();
        if (siteDAOInstance.save(rg_siteEntity)) {
        } else {
            System.out.println("保存失败");
            WebSocketNotification.broadcast("保存失败");
        }
    }

    public void delete() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        SiteDAOImpl siteDAOInstance = DAOFactory.getSiteInstance();
        if (siteDAOInstance.delete(rg_siteEntity)) {
        } else {
            System.out.println("删除失败");
            WebSocketNotification.broadcast("删除失败");
        }
    }

    public void update() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        RG_SiteEntity rg_siteEntity = Tools.jsonConvertToEntity(jsonString, RG_SiteEntity.class);
        SiteDAOImpl siteDAOInstance = DAOFactory.getSiteInstance();
        if (siteDAOInstance.update(rg_siteEntity)) {
        } else {
            System.out.println("更新失败");
            WebSocketNotification.broadcast("更新失败");
        }

    }

    public void findAllById() throws Exception {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        String siteId = jsonNode.get("id").asText();
        SiteDAOImpl siteDAO = DAOFactory.getSiteInstance();
        RG_SiteEntity rg_siteEntity = siteDAO.findAllById(siteId);
        String resultString = Tools.entityConvertToJsonString(rg_siteEntity);
        Tools.jsonPrint(resultString, this.httpServletResponse);
    }
}
