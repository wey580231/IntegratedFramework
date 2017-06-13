package com.rengu.util;

import com.rengu.DAO.impl.*;

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

    public static AssisantprocessDAOImpl getAssisantprocessDAOInstance() {
        return new AssisantprocessDAOImpl();
    }

    public static GroupResourceDAOImpl getGroupResourceInstance() {
        return new GroupResourceDAOImpl();
    }

    public static ResourceDAOImpl getResourceInstance() {
        return new ResourceDAOImpl();
    }

    public static ShiftDAOImpl getShiftInstance() {
        return new ShiftDAOImpl();
    }

    public static SiteDAOImpl getSiteInstance() {
        return new SiteDAOImpl();
    }

    public static TyperescourceDAOImpl getTyperescourceInstance() {
        return new TyperescourceDAOImpl();
    }

    public static ScheduleDAOImpl getScheduleDAOImplInstance() {
        return new ScheduleDAOImpl();
    }

    public static LayoutDAOImpl getLayoutDAOImplInstance() {
        return new LayoutDAOImpl();
    }

    public static ClubDAOImpl getClubDAOImplInstance() {
        return new ClubDAOImpl();
    }
}
