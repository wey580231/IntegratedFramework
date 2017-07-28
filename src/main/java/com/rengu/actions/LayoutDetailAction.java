package com.rengu.actions;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.DAO.impl.LayoutDetailDAOImpl;
import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.DAO.impl.SnapshotDaoImpl;
import com.rengu.entity.*;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by XY on 2017/7/28.
 */
public class LayoutDetailAction extends SuperAction {

    public void findAllById() throws Exception {

        /*JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(httpServletRequest));*/
        String string = Tools.getHttpRequestBody(httpServletRequest);
        JsonNode jsonNode = Tools.jsonTreeModelParse(string);
        String layoutId = jsonNode.get("id").asText();
        RG_LayoutEntity rg_layoutEntity = DAOFactory.getLayoutDAOImplInstance().findAllById(layoutId);
        Set<RG_LayoutDetailEntity> rg_layoutDetailEntitySet = rg_layoutEntity.getDetails();
        if (rg_layoutDetailEntitySet.size() > 0) {
            //根节点
            JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
            //数组
            ArrayNode arrayNode = jsonNodeFactory.arrayNode();
            for (RG_LayoutDetailEntity rg_layoutDetailEntity: rg_layoutDetailEntitySet){
                ObjectNode objectNode = jsonNodeFactory.objectNode();
                String resourceId = rg_layoutDetailEntity.getItem();
                RG_ResourceEntity rg_resourceEntity= DAOFactory.getResourceInstance().findAllById(resourceId);
                if (rg_resourceEntity != null){
                    objectNode.put("item", rg_resourceEntity.getName());
                    objectNode.put("pos", rg_layoutDetailEntity.getPos());
                    objectNode.put("state", rg_layoutDetailEntity.getState());
                    objectNode.put("exist", rg_layoutDetailEntity.getExist());
                    arrayNode.add(objectNode);
                }
            }
            String jsonString = new ObjectMapper().writeValueAsString(arrayNode);
            Tools.jsonPrint(jsonString, this.httpServletResponse);
        } else {
            Tools.jsonPrint(null, this.httpServletResponse);
        }


    }

}
