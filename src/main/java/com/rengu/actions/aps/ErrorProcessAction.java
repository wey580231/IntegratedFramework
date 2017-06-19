package com.rengu.actions.aps;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.aps.ErrorProcessDao;
import com.rengu.actions.SuperAction;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.util.ApsTools;
import com.rengu.util.GlobalVariable;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * aps异常处理中心，处理工序、设备、紧急插单等异常
 * Created by wey580231 on 2017/6/15.
 */
public class ErrorProcessAction extends SuperAction {

    ErrorProcessDao dao = new ErrorProcessDao();

    public void processDeviceError() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        Integer result = ApsTools.UNKNOWN;

        if (parameterMap.size() == 1) {
            String[] id = (String[]) parameterMap.get("id");
            if (id.length == 1) {
                result = dao.processDeviceError(id[0]);
            }
        }

        if (result == ApsTools.STARTED) {
            Tools.jsonPrint(Tools.resultCode("ok", "Aps is computing..."), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }
}