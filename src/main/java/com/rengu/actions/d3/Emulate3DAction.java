package com.rengu.actions.d3;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.d3.Emulate3DAO;
import com.rengu.actions.SuperAction;
import com.rengu.util.Tools;

import java.util.Map;

/**
 * 仿真或实时数据处理
 * Created by wey580231 on 2017/6/6.
 */
public class Emulate3DAction extends SuperAction {

    Emulate3DAO emulateDao = new Emulate3DAO();

    //【已调】获取模拟的数据流，暂定将最近一次的排程结果进行转换后，发回给3D车间
    public void getEmulateFlow() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;
        StringBuilder jsonString = new StringBuilder();

        if (parameterMap.size() == 1) {
            String[] types = (String[]) parameterMap.get("snapshotId");
            if (types.length > 0) {
                result = emulateDao.getEmulateData(types[0], jsonString);
//                result = emulateDao.getEmulateResult(types[0], jsonString);
            }
        }

        if (result) {
            Tools.jsonPrint(jsonString.toString(), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("1", "Can't execute operation"), this.httpServletResponse);
        }
    }

    //TODO 获取实时数据流，等MES具备后再调整，预计到6月底
    public void getRealFlow() {


    }
}
