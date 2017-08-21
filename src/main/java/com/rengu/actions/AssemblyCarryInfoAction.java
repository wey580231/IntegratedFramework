package com.rengu.actions;

import com.rengu.DAO.AssemblyCarryInfoDAO;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by XY on 2017/8/18.
 */
public class AssemblyCarryInfoAction extends SuperAction {
    public void getAllAssemblyCarrys() throws Exception {
        AssemblyCarryInfoDAO assemblyCarryInfoDAO = DAOFactory.getAssemblyCarryInfoDAOImplInstance();
        List list = assemblyCarryInfoDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

}
