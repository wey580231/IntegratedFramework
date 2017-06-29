package com.rengu.actions.d3;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.d3.Device3DAO;
import com.rengu.actions.SuperAction;
import com.rengu.util.Tools;

import java.util.Map;

/**
 * 用于处理3D车间对设备和订单报表的请求
 * Created by wey580231 on 2017/6/6.
 */
public class Report3DAction extends SuperAction {

    private final String requestDevice = "device";
    private final String requestOrder = "order";

    Device3DAO deviceDao = new Device3DAO();

    //【已调】根据3D车间的请求类型，返回对应的报表信息
    public void resport3d() {

        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean opresult = false;
        StringBuilder jsonString = new StringBuilder();

        if (parameterMap.size() == 2) {
            String[] types = (String[]) parameterMap.get("requestType");
            String[] codes = (String[]) parameterMap.get("requestCode");
            if (types.length == 1 && codes.length == 1) {
                String requestType = types[0];
                String requestCode = codes[0];
                if (requestType.toLowerCase().equals(requestDevice) && codes[0].length() > 0) {
                    opresult = deviceDao.getDeviceReport(requestDevice, requestCode,jsonString);
                } else if (requestType.toLowerCase().equals(requestOrder) && codes[0].length() > 0) {
                    opresult = deviceDao.getOrderReport(requestOrder,requestCode, jsonString);
                }
            }
        }

        if (opresult) {
            Tools.jsonPrint(jsonString.toString(), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }
}
