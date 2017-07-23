package com.rengu.actions;

import com.rengu.entity.RG_AdjustLayoutEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

public class AdjustLayoutAction extends SuperAction {
    public void getAllAdjustLayoutException() throws Exception {
        List<RG_AdjustLayoutEntity> adjustLayoutEntityList = DAOFactory.getAdjustLayoutDAOImplInstance().findAll();
        String jsonString = Tools.entityConvertToJsonString(adjustLayoutEntityList);
        Tools.jsonPrint(jsonString, httpServletResponse);
    }
}
