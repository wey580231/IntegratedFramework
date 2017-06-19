package com.rengu.actions;

import com.rengu.DAO.impl.AdjustDeviceDAOImpl;
import com.rengu.DAO.impl.AdjustProcessDAOImpl;
import com.rengu.entity.RG_AdjustDeviceEntity;
import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by wey580231 on 2017/6/19.
 */
public class AdjustDeviceAction extends SuperAction{

    public void getAllAdjustDeviceException() throws Exception {

        AdjustDeviceDAOImpl adjustProcessDAO = DAOFactory.getAdjustDeviceDAOImplInstance();
        List<RG_AdjustDeviceEntity> adjustDeviceEntityList = adjustProcessDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(adjustDeviceEntityList);
        Tools.jsonPrint(jsonString, httpServletResponse);

    }

}
