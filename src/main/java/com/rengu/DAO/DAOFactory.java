package com.rengu.DAO;

import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.DAO.impl.UsersDAOImpl;

/**
 * Created by hanchangming on 2017/5/22.
 */
public class DAOFactory {
    public static UsersDAOImpl getUserDAOInstance() {
        return new UsersDAOImpl();
    }

    public static OrdersDAOImpl getOrdersDAOInstance() {
        return new OrdersDAOImpl();
    }
}
