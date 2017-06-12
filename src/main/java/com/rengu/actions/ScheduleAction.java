package com.rengu.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.APS.APSTools;
import com.rengu.entity.RG_LayoutEntity;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.EntityConvertToSQL;
import com.rengu.util.Info;
import com.rengu.util.Tools;

import java.util.Iterator;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class ScheduleAction extends SuperAction {

    public void beginSchedule() throws Exception {
        String jsonString = Tools.getHttpRequestBody(this.httpServletRequest);
        JsonNode rootNode = Tools.jsonTreeModelParse(jsonString);
        //解析订单数据
        JsonNode orderNodes = rootNode.get("orders");
        for (JsonNode tempNode : orderNodes) {
            String orderNodeJsonString = tempNode.toString();
            RG_OrderEntity rg_orderEntity = Tools.jsonConvertToEntity(orderNodeJsonString, RG_OrderEntity.class);
            Tools.executeSQLForUpdate(Info.MySQL, Info.APS, EntityConvertToSQL.insertSQLForAPS(rg_orderEntity));
        }
        //解析APS_Config数据
        JsonNode APS_ConfigNode = rootNode.get("APSConfig");
        for (Iterator<String> it = APS_ConfigNode.fieldNames(); it.hasNext(); ) {
            String APS_ConfigNodeKey = it.next();
            String APS_ConfigNodeValue = APS_ConfigNode.get(APS_ConfigNodeKey).asText();
            Tools.executeSQLForUpdate(Info.MySQL, Info.APS, APSTools.insertAPSConfigSQL(APS_ConfigNodeKey, APS_ConfigNodeValue));
        }
        //解析Layout数据
        JsonNode layoutNode = rootNode.get("layout");
        RG_LayoutEntity rg_layoutEntity = Tools.jsonConvertToEntity(layoutNode.textValue(), RG_LayoutEntity.class);
    }
}