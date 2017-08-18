package com.rengu.actions;

import com.rengu.DAO.CarryDAO;
import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

/**
 * Created by XY on 2017/8/17.
 */
public class CarryAction extends SuperAction {
    public void getAllCarrys() throws Exception {
        CarryDAO carryDAO = DAOFactory.getCarryDAOImplInstance();
        List list = carryDAO.findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }

}
