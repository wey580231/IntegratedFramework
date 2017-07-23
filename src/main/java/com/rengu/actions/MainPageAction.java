package com.rengu.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.util.DAOFactory;
import com.rengu.util.ErrorState;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by hanch on 2017/7/20.
 */
public class MainPageAction extends SuperAction {
    public void getAllExcepitonNumInfo() throws Exception {
        int deviceExcepitonNum = getListSize(DAOFactory.getAdjustDeviceDAOImplInstance().findAll());
        int deviceExcepitonHandledNum = getListSize(DAOFactory.getAdjustDeviceDAOImplInstance().findAllByErrorState(ErrorState.ERROR_APS_FINISH)) + getListSize(DAOFactory.getAdjustDeviceDAOImplInstance().findAllByErrorState(ErrorState.ERROR_ADJUSTED));
        int orderExcepitonNum = getListSize(DAOFactory.getAdjustOrderDAOImplInstance().findAll());
        int orderExcepitonHandledNum = getListSize(DAOFactory.getAdjustOrderDAOImplInstance().findAllByErrorState(ErrorState.ERROR_APS_FINISH)) + getListSize(DAOFactory.getAdjustOrderDAOImplInstance().findAllByErrorState(ErrorState.ERROR_ADJUSTED));
        int processExcepitonNum = getListSize(DAOFactory.getAdjustProcessDAOImplInstance().findAll());
        int processExcepitonHandledNum = getListSize(DAOFactory.getAdjustProcessDAOImplInstance().findAllByErrorState(ErrorState.ERROR_APS_FINISH)) + getListSize(DAOFactory.getAdjustProcessDAOImplInstance().findAllByErrorState(ErrorState.ERROR_ADJUSTED));
        int layoutExcepitonNum = getListSize(DAOFactory.getAdjustLayoutDAOImplInstance().findAll());
        int layoutExcepitonHandledNum = getListSize(DAOFactory.getAdjustLayoutDAOImplInstance().findAllByErrorState(ErrorState.ERROR_APS_FINISH)) + getListSize(DAOFactory.getAdjustLayoutDAOImplInstance().findAllByErrorState(ErrorState.ERROR_ADJUSTED));
        //生成根节点树
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        objectNode.put("deviceExcepitonNum", deviceExcepitonNum);
        objectNode.put("deviceExcepitonHandledNum", deviceExcepitonHandledNum);
        objectNode.put("orderExcepitonNum", orderExcepitonNum);
        objectNode.put("orderExcepitonHandledNum", orderExcepitonHandledNum);
        objectNode.put("processExcepitonNum", processExcepitonNum);
        objectNode.put("processExcepitonHandledNum", processExcepitonHandledNum);
        objectNode.put("layoutExcepitonNum", layoutExcepitonNum);
        objectNode.put("layoutExcepitonHandledNum", layoutExcepitonHandledNum);
        try {
            String jsonString = new ObjectMapper().writeValueAsString(objectNode);
            Tools.jsonPrint(jsonString, httpServletResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    //从List获取长度的方法
    private int getListSize(List list) {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }
}

