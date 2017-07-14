package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.AdjustOrderDAOImpl;
import com.rengu.entity.RG_AdjustOrderEntity;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.ApsTools;
import com.rengu.util.DAOFactory;
import com.rengu.util.ErrorState;
import com.rengu.util.Tools;

import java.util.List;

/**
 * 订单调整(紧急插单)
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustOrderAction extends SuperAction {
    public void getAllAdjustOrderException() throws Exception {
        AdjustOrderDAOImpl adjustOrderDAO = DAOFactory.getAdjustOrderDAOImplInstance();
        List<RG_AdjustOrderEntity> adjustOrderEntityList = adjustOrderDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(adjustOrderEntityList);
        Tools.jsonPrint(jsonString, httpServletResponse);
    }

    public void creatOrderException() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        RG_AdjustOrderEntity rg_adjustOrderEntity = new RG_AdjustOrderEntity();
        rg_adjustOrderEntity.setId(Tools.getUUID());
        rg_adjustOrderEntity.setOrigin("手工模拟");
        rg_adjustOrderEntity.setState(ErrorState.ERROR_UNSOLVED);
        //订单对象深复制
        RG_OrderEntity rg_orderEntity = new RG_OrderEntity();
        RG_OrderEntity tempOrderEntity = DAOFactory.getOrdersDAOInstance().findAllById("Yqc");
        rg_orderEntity.setId(Tools.getUUID());
        rg_orderEntity.setName(tempOrderEntity.getName());
        rg_orderEntity.setQuantity(tempOrderEntity.getQuantity());
        rg_orderEntity.setPriority(tempOrderEntity.getPriority());
        rg_orderEntity.setT0(Tools.stringConvertToDate(jsonNode.get("t0").asLong()));
        rg_orderEntity.setT1(Tools.stringConvertToDate(jsonNode.get("t1").asLong()));
        rg_orderEntity.setT2(Tools.stringConvertToDate(jsonNode.get("t2").asLong()));
        rg_orderEntity.setT1Plan(tempOrderEntity.getT1Plan());
        rg_orderEntity.setT2Plan(tempOrderEntity.getT2Plan());
        rg_orderEntity.setEstimate(tempOrderEntity.getEstimate());
        rg_orderEntity.setDelay(tempOrderEntity.getDelay());
        rg_orderEntity.setColor(tempOrderEntity.getColor());
        rg_orderEntity.setState(tempOrderEntity.getState());
        rg_orderEntity.setSelected(tempOrderEntity.getSelected());
        rg_orderEntity.setNbTask(tempOrderEntity.getNbTask());
        rg_orderEntity.setFinished(tempOrderEntity.isFinished());
        rg_orderEntity.setProductByIdProduct(tempOrderEntity.getProductByIdProduct());
        rg_orderEntity.setClubByIdClub(tempOrderEntity.getClubByIdClub());
        DAOFactory.getOrdersDAOInstance().save(rg_orderEntity);
        rg_adjustOrderEntity.setOrd(rg_orderEntity);
        DAOFactory.getAdjustOrderDAOImplInstance().save(rg_adjustOrderEntity);
    }

    public void OrderExceptionHandling() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        String adjustOrderId = jsonNode.get("id").asText();
        RG_AdjustOrderEntity rg_adjustOrderEntity = DAOFactory.getAdjustOrderDAOImplInstance().findAllById(adjustOrderId);
        ApsTools.instance().executeCommand(ApsTools.getAdjustOrderHandlingURL(rg_adjustOrderEntity));
    }
}
