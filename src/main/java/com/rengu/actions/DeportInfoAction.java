package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.AssemblyCarryInfoDAO;
import com.rengu.DAO.impl.DeportInfoDAOImpl;
import com.rengu.DAO.impl.ProcessDAOImpl;
import com.rengu.entity.RG_Mes_DeportInfo;
import com.rengu.entity.RG_ProcessEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by XY on 2017/8/21.
 */
public class DeportInfoAction extends SuperAction  {

    public void getAllDeportInfo() throws Exception {
        DeportInfoDAOImpl deportInfoDAO = DAOFactory.getDeportInfoDAOImplInstance();
        List list = deportInfoDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void getAllById() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        String deportInfoId = jsonNode.get("id").asText();
        DeportInfoDAOImpl deportInfoDAO = DAOFactory.getDeportInfoDAOImplInstance();
        RG_Mes_DeportInfo rg_deportInfoEntity = deportInfoDAO.findAllById(deportInfoId);
        String jsonString = Tools.entityConvertToJsonString(rg_deportInfoEntity);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

}
