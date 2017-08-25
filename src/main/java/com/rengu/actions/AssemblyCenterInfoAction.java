package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.AssemblyCenterInfoDAO;
import com.rengu.entity.PageEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.io.IOException;
import java.util.List;

/**
 * Created by XY on 2017/8/18.
 */
public class AssemblyCenterInfoAction extends SuperAction {
    public void getAllAssemblyCenters() throws Exception {
        AssemblyCenterInfoDAO assemblyCenterInfoDAO = DAOFactory.getAssemblyCenterInfoDAOImplInstance();
        List list = assemblyCenterInfoDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void getAllAssemblyCentersByFirstResultAndMaxResults() throws IOException {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        int firstResult = jsonNode.get("firstResult").asInt();
        int maxResults = jsonNode.get("maxResults").asInt();
        int totalNum = DAOFactory.getAssemblyCenterInfoDAOImplInstance().getTotalNum();
        List list = DAOFactory.getAssemblyCenterInfoDAOImplInstance().findAllByFirstResultAndMaxResults(firstResult, maxResults);
        PageEntity pageEntity = new PageEntity(totalNum, firstResult, maxResults, list);
        Tools.jsonPrint(Tools.entityConvertToJsonString(pageEntity), this.httpServletResponse);
    }
}
