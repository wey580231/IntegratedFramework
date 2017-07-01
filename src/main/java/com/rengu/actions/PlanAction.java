package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.PlanDAOImpl;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by hanch on 2017/7/1.
 */
public class PlanAction extends SuperAction {
    public void getAllPlanBySnapshotId() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        String snapshotId = jsonNode.get("id").asText();
        PlanDAOImpl planDAO = DAOFactory.getPlanDAOImplInstance();
        List<RG_PlanEntity> rg_planEntityList = planDAO.findAllBySnapshotId(snapshotId);
        String jsonString = Tools.entityConvertToJsonString(rg_planEntityList);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }
}
