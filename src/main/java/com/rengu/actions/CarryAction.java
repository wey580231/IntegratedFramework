package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.CarryDAO;
import com.rengu.entity.PageEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.io.IOException;
import java.util.List;

/**
 * Created by XY on 2017/8/17.
 */
public class CarryAction extends SuperAction {
    public void getAllCarrys() throws Exception {
        CarryDAO carryDAO = DAOFactory.getCarryDAOImplInstance();
        List list = carryDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

    public void getAllCarrysByFirstResultAndMaxResults() throws IOException {
        String jsonString = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(jsonString);
        int firstResult = jsonNode.get("firstResult").asInt();
        int maxResults = jsonNode.get("maxResults").asInt();
        int totalNum = DAOFactory.getCarryDAOImplInstance().getTotalNum();
        List list = DAOFactory.getCarryDAOImplInstance().findAllByFirstResultAndMaxResults(firstResult, maxResults);
        PageEntity pageEntity = new PageEntity(totalNum, firstResult, maxResults, list);
        Tools.jsonPrint(Tools.entityConvertToJsonString(pageEntity), this.httpServletResponse);
    }
}
