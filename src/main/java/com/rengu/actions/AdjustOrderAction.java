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

    public void getAllAdjustOrderByAdjustOrderType() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        String adjustOrderType = jsonNode.get("adjustOrderType").asText();
        List<RG_AdjustOrderEntity> rg_adjustOrderEntityList = DAOFactory.getAdjustOrderDAOImplInstance().findAllByAdjustOrderType(adjustOrderType);
        Tools.jsonPrint(Tools.entityConvertToJsonString(rg_adjustOrderEntityList), httpServletResponse);
    }

    public void creatOrderException() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        RG_AdjustOrderEntity rg_adjustOrderEntity = new RG_AdjustOrderEntity();
        rg_adjustOrderEntity.setId(Tools.getUUID());
        rg_adjustOrderEntity.setOrigin("手工模拟");
        rg_adjustOrderEntity.setState(ErrorState.ERROR_UNSOLVED);
        //订单对象深复制
        RG_OrderEntity rg_orderEntity = new RG_OrderEntity();
        rg_orderEntity.setId(Tools.getUUID());
        rg_orderEntity.setName(jsonNode.get("name").asText());
        rg_orderEntity.setQuantity(Short.parseShort(jsonNode.get("quantity").asText()));
        rg_orderEntity.setPriority(Short.parseShort(jsonNode.get("priority").asText()));
        rg_orderEntity.setT0(Tools.stringConvertToDate(jsonNode.get("t0").asLong()));
        rg_orderEntity.setT1(Tools.stringConvertToDate(jsonNode.get("t1").asLong()));
        rg_orderEntity.setT2(Tools.stringConvertToDate(jsonNode.get("t2").asLong()));
        rg_orderEntity.setT1Plan(Tools.stringConvertToDate(jsonNode.get("t1plan").asLong()));
        rg_orderEntity.setT2Plan(Tools.stringConvertToDate(jsonNode.get("t2plan").asLong()));
        rg_orderEntity.setEstimate(Short.parseShort(jsonNode.get("estimate").asText()));
        rg_orderEntity.setDelay(Short.parseShort(jsonNode.get("delay").asText()));
        rg_orderEntity.setColor(jsonNode.get("color").asText());
        rg_orderEntity.setState(Byte.parseByte(jsonNode.get("state").asText()));
        rg_orderEntity.setSelected(Byte.parseByte(jsonNode.get("selected").asText()));
        rg_orderEntity.setNbTask(Short.parseShort(jsonNode.get("nbtask").asText()));
        rg_orderEntity.setFinished(Boolean.parseBoolean(jsonNode.get("finished").asText()));
        rg_orderEntity.setProductByIdProduct(DAOFactory.getProductDAOImplInstance().findAllById(jsonNode.get("idProduct").asText()));
        rg_orderEntity.setClubByIdClub(DAOFactory.getClubDAOImplInstance().findAllById(jsonNode.get("idClub").asText()));
        DAOFactory.getOrdersDAOInstance().save(rg_orderEntity);
        rg_adjustOrderEntity.setOrd(rg_orderEntity);
        DAOFactory.getAdjustOrderDAOImplInstance().save(rg_adjustOrderEntity);
    }

    //紧急插单处理
    public void OrderExceptionHandling() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));
        String adjustOrderId = jsonNode.get("id").asText();
        RG_AdjustOrderEntity rg_adjustOrderEntity = DAOFactory.getAdjustOrderDAOImplInstance().findAllById(adjustOrderId);
        ApsTools.instance().executeCommand(ApsTools.getAdjustOrderHandlingURL(rg_adjustOrderEntity));
    }
}
