package com.rengu.actions;

import com.rengu.DAO.impl.AdjustOrderDAOImpl;
import com.rengu.entity.RG_AdjustOrderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**订单调整(紧急插单)
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustOrderAction extends SuperAction {
    public void getAllAdjustOrderException() throws Exception {
        AdjustOrderDAOImpl adjustOrderDAO = DAOFactory.getAdjustOrderDAOImplInstance();
        List<RG_AdjustOrderEntity> adjustOrderEntityList = adjustOrderDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(adjustOrderEntityList);
        Tools.jsonPrint(jsonString, httpServletResponse);
    }
}
