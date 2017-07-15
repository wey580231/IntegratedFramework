package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.AdjustProcessDAOImpl;
import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.util.ApsTools;
import com.rengu.util.DAOFactory;
import com.rengu.util.ErrorState;
import com.rengu.util.Tools;

import java.util.List;

/**
 * 工序异常
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustProcessAction extends SuperAction {

    public void getAllAdjustProcessException() throws Exception {
        AdjustProcessDAOImpl adjustProcessDAO = DAOFactory.getAdjustProcessDAOImplInstance();
        List<RG_AdjustProcessEntity> adjustProcessEntityList = adjustProcessDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(adjustProcessEntityList);
        Tools.jsonPrint(jsonString, httpServletResponse);
    }

    public void creatProcessException() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        RG_AdjustProcessEntity rg_adjustProcessEntity = new RG_AdjustProcessEntity();
        rg_adjustProcessEntity.setId(Tools.getUUID());
        rg_adjustProcessEntity.setOrigin("手工模拟");
        rg_adjustProcessEntity.setState(ErrorState.ERROR_UNSOLVED);
        rg_adjustProcessEntity.setReportTime(Tools.parseDate(jsonNode.get("reportTime").asText()));
        rg_adjustProcessEntity.setIdTask(jsonNode.get("idTask").asText());
        rg_adjustProcessEntity.setIdJob(jsonNode.get("idJob").asText());
        rg_adjustProcessEntity.setIdOrder(jsonNode.get("idOrder").asText());
        rg_adjustProcessEntity.setOriginalResource(jsonNode.get("originalResource").asText());
        rg_adjustProcessEntity.setOriginalStartTime(Tools.parseDate(jsonNode.get("originalStartTime").asText()));
        rg_adjustProcessEntity.setAppointResource(jsonNode.get("appointResource").asText());
        rg_adjustProcessEntity.setAppointStartTime(Tools.parseDate(jsonNode.get("appointStartTime").asText()));
        DAOFactory.getAdjustProcessDAOImplInstance().save(rg_adjustProcessEntity);
    }

    public void processExceptionHandling() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        String adjustOrderId = jsonNode.get("id").asText();
        RG_AdjustProcessEntity rg_adjustProcessEntity = DAOFactory.getAdjustProcessDAOImplInstance().findAllById(adjustOrderId);
        ApsTools.instance().executeCommand(ApsTools.getAdjustProcessHandlingURL(rg_adjustProcessEntity));
    }
}
