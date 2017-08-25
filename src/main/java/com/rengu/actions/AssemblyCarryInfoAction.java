package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.AssemblyCarryInfoDAO;
import com.rengu.entity.PageEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.io.IOException;
import java.util.List;

/**
 * Created by XY on 2017/8/18.
 */
public class AssemblyCarryInfoAction extends SuperAction {
    public void getAllAssemblyCarrys() throws Exception {
        AssemblyCarryInfoDAO assemblyCarryInfoDAO = DAOFactory.getAssemblyCarryInfoDAOImplInstance();
        List list = assemblyCarryInfoDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void getAllAssemblyCarrysByFirstResultAndMaxResults() throws IOException {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        int firstResult = jsonNode.get("firstResult").asInt();
        int maxResults = jsonNode.get("maxResults").asInt();
        int totalNum = DAOFactory.getAssemblyCarryInfoDAOImplInstance().getTotalNum();
        List list = DAOFactory.getAssemblyCarryInfoDAOImplInstance().findAllByFirstResultAndMaxResults(firstResult, maxResults);
        PageEntity pageEntity = new PageEntity(totalNum, firstResult, maxResults, list);
        Tools.jsonPrint(Tools.entityConvertToJsonString(pageEntity), this.httpServletResponse);
    }
}
