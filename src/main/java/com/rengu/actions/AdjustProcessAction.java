package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.AdjustProcessDAOImpl;
import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.util.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
        RG_SnapshotNodeEntity rg_snapshotNodeEntity = DAOFactory.getSnapshotDaoImplInstance().findAllById(UserConfigTools.getLatestDispatchMesId("1"));
        Set<RG_PlanEntity> rg_planEntities = rg_snapshotNodeEntity.getPlans();
        Iterator<RG_PlanEntity> rg_planEntities1 = rg_planEntities.iterator();
        RG_PlanEntity rg_planEntity = rg_planEntities1.next();
        RG_AdjustProcessEntity rg_adjustProcessEntity = new RG_AdjustProcessEntity();
        rg_adjustProcessEntity.setId(Tools.getUUID());
        rg_adjustProcessEntity.setReportTime(new Date());
        rg_adjustProcessEntity.setIdTask(rg_planEntity.getIdTask());
        rg_adjustProcessEntity.setIdJob(rg_planEntity.getIdJob());
        rg_adjustProcessEntity.setIdOrder(rg_planEntity.getOrderByIdOrder().getId());
        rg_adjustProcessEntity.setOriginalResource(rg_planEntity.getResourceByIdResource().getIdR());
        rg_adjustProcessEntity.setAppointResource(rg_planEntity.getResourceByIdResource().getIdR());
        long steam = Tools.stringConvertToDate(rg_planEntity.getT1Task()).getTime() + 60 * 60 * 1000 * 4;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateTime = simpleDateFormat.parse(simpleDateFormat.format(steam));
        rg_adjustProcessEntity.setAppointStartTime(dateTime);
        rg_adjustProcessEntity.setOriginalStartTime(Tools.stringConvertToDate(rg_planEntity.getT1Task()));
        rg_adjustProcessEntity.setOrigin("手工模拟");
        rg_adjustProcessEntity.setState(ErrorState.ERROR_UNSOLVED);
        DAOFactory.getAdjustProcessDAOImplInstance().save(rg_adjustProcessEntity);
        Tools.creatNotificationMessage("产生工序拖期异常", "alert");
    }

    public void processExceptionHandling() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        String adjustOrderId = jsonNode.get("id").asText();
        RG_AdjustProcessEntity rg_adjustProcessEntity = DAOFactory.getAdjustProcessDAOImplInstance().findAllById(adjustOrderId);
        ApsTools.instance().executeCommand(ApsTools.getAdjustProcessHandlingURL(rg_adjustProcessEntity));
    }
}
