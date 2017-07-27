package com.rengu.actions;

import com.rengu.util.DAOFactory;
import com.rengu.util.Tools;

import java.util.List;

public class ProductAction extends SuperAction {
    public void getAllProduct() throws Exception {
        List list = DAOFactory.getProductDAOImplInstance().findAll();
        String jsonString = Tools.entityConvertToJsonString(list);
        Tools.jsonPrint(jsonString, this.httpServletResponse);
    }
}