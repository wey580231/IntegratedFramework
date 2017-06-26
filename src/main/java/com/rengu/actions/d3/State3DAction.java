package com.rengu.actions.d3;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.d3.State3DAO;
import com.rengu.actions.SuperAction;
import com.rengu.entity.RG_State3DEntity;
import com.rengu.util.Tools;

import java.util.Map;

/**
 * 用于接收3D车间的状态查询、布局请求、设置布局信息等
 * Created by wey580231 on 2017/6/5.
 */
public class State3DAction extends SuperAction {

    RG_State3DEntity state3DEntity;

    State3DAO stateDao = new State3DAO();

    //【已调】查询3D总体状态
    public void queryState() {
        Tools.jsonPrint(stateDao.getCurrentState(), this.httpServletResponse);
    }

    //【已调】根据id查询布局信息
    public void config3DLayout() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        if (parameterMap.size() > 0) {
            String[] layOutId = (String[]) parameterMap.get("id");
            if (layOutId.length == 1) {
                String result = stateDao.getLayoutById(layOutId[0]);
                if (result.length() > 0) {
                    Tools.jsonPrint(result, this.httpServletResponse);
                    return;
                }
            }
        }

        Tools.jsonPrint(Tools.resultCode("error", "Can't find layout info"), this.httpServletResponse);
    }

    //【已调】插入或更新布局信息
    public void insertOrUpdateLayout() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();
        boolean opresult = false;
        if (parameterMap.size() >= 3) {
            String[] operateState = (String[]) parameterMap.get("operateState");
            String[] layoutId = (String[]) parameterMap.get("layoutId");
            String[] data = (String[]) parameterMap.get("data");

            if (operateState.length == 1 && layoutId.length == 1 && data.length == 1) {
                if (operateState[0].equals("update")) {
                    opresult = stateDao.updateLayout(layoutId[0], data[0]);
                } else if (operateState[0].equals("new")) {
                    opresult = stateDao.insertLayout(layoutId[0], data[0]);
                }
            }
        }

        if (opresult) {
            Tools.jsonPrint(Tools.resultCode("ok", "Operation success"), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }

    //根据layoutId和设备id更新设备状态
    public void updateDeviceState(){
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();
        boolean opresult = false;
        if (parameterMap.size() >= 2) {
            String[] layoutId = (String[]) parameterMap.get("layoutId");
            String[] data = (String[]) parameterMap.get("data");

            if (layoutId.length == 1 && data.length == 1) {
                opresult = stateDao.updateDevice(layoutId[0],data[0]);
            }
        }

        if (opresult) {
            Tools.jsonPrint(Tools.resultCode("ok", "Operation success"), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }

    //【已调】查询所有布局信息
    public void query3DLayout(){
        Tools.jsonPrint(stateDao.queryAllLayout(), this.httpServletResponse);
    }

    public RG_State3DEntity getState3DEntity() {
        return state3DEntity;
    }

    public void setState3DEntity(RG_State3DEntity state3DEntity) {
        this.state3DEntity = state3DEntity;
    }
}
