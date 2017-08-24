package com.rengu.actions;

import com.rengu.DAO.AGVInfoDAO;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by XY on 2017/8/23.
 */
public class AGVInfoAction extends SuperAction {
    public void getAllAGVInfo() throws Exception {
        AGVInfoDAO agvInfoDAO = DAOFactory.getAGVInfoDAOImplInstance();
        List list = agvInfoDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

}
