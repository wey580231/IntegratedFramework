package com.rengu.actions;

import com.rengu.DAO.LayoutDAO;
import com.rengu.DAO.d3.State3DAO;
import com.rengu.entity.RG_State3DEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by wey580231 on 2017/7/15.
 */
public class LayoutAction extends  SuperAction{

    RG_State3DEntity state3DEntity;

    State3DAO stateDao = new State3DAO();

    public void query3DLayout(){
        Tools.jsonPrint(stateDao.queryAllLayoutFullInfo(), this.httpServletResponse);
    }

    public void getAllFactoryLayouts() throws Exception {
        LayoutDAO factoryLayoutDAO = DAOFactory.getLayoutDAOImplInstance();
        List list = factoryLayoutDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }
}
