package com.rengu.actions;

import com.rengu.DAO.AssemblyCarryInfoDAO;
import com.rengu.DAO.AssemblyCenterInfoDAO;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by XY on 2017/8/18.
 */
public class AssemblyCenterInfoAction extends SuperAction {
    public void getAllAssemblyCenters() throws Exception {
        AssemblyCenterInfoDAO assemblyCenterInfoDAO = DAOFactory.getAssemblyCenterInfoDAOImplInstance();
        List list = assemblyCenterInfoDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

}
