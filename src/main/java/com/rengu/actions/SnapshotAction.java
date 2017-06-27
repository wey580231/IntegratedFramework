package com.rengu.actions;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.SnapshotDao;
import com.rengu.util.Tools;

import java.util.Map;

/**
 * 快照树管理操作，用于查看快照节点的3D模拟/用于将选中快照下发给MES
 * Created by wey580231 on 2017/6/27.
 */
public class SnapshotAction extends SuperAction {

    SnapshotDao snapshotDao = new SnapshotDao();

    //查看当前快照对应结果在3的车间中的运行状态
    public void viewCurrentSnapshotEmulateData() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean opresult = false;
        StringBuilder jsonString = new StringBuilder();

        if (parameterMap.size() == 1) {
            String[] ids = (String[]) parameterMap.get("id");
            if (ids.length == 1) {
                opresult = snapshotDao.switchToEmulateData("1", ids[0]);
            }
        }

        if (opresult) {
            Tools.jsonPrint(Tools.resultCode("ok", "Execute operation"), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }

    //将当前选中的快照节点对应的结果下发给MES
    public void dispatcherResultToMess() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean opresult = false;
        StringBuilder jsonString = new StringBuilder();

        if (parameterMap.size() == 1) {
            String[] ids = (String[]) parameterMap.get("id");
            if (ids.length == 1) {
                opresult = snapshotDao.switchResultToMess("1", ids[0]);
            }
        }

        if (opresult) {
            Tools.jsonPrint(Tools.resultCode("ok", "Execute operation"), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }
}
