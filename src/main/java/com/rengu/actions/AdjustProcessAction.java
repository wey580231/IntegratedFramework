package com.rengu.actions;

import com.rengu.DAO.impl.AdjustProcessDAOImpl;
import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustProcessAction extends SuperAction {
    public void getAllAdjustProcessException() throws Exception {
        AdjustProcessDAOImpl adjustProcessDAO = DAOFactory.getAdjustProcessDAOImplInstance();
        List<RG_AdjustProcessEntity> adjustProcessEntityList = adjustProcessDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(adjustProcessEntityList);
        Tools.jsonPrint(jsonString, httpServletResponse);
        System.out.println(jsonString);
    }
}
