package com.rengu.util;

import com.rengu.DAO.impl.*;

/**
 * Created by hanchangming on 2017/5/22.
 */
public class DAOFactory {

    private static final UsersDAOImpl usersDAOImpl = new UsersDAOImpl();
    private static final OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
    private static final AssisantprocessDAOImpl assisantprocessDAOimpl = new AssisantprocessDAOImpl();
    private static final GroupResourceDAOImpl groupResourceDAOImpl = new GroupResourceDAOImpl();
    private static final ResourceDAOImpl resourceDAOImpl = new ResourceDAOImpl();
    private static final ShiftDAOImpl shiftDAOImpl = new ShiftDAOImpl();
    private static final SiteDAOImpl siteDAOImpl = new SiteDAOImpl();
    private static final TyperescourceDAOImpl typerescourceDAOImpl = new TyperescourceDAOImpl();
    private static final ScheduleDAOImpl scheduleDAOImpl = new ScheduleDAOImpl();
    private static final LayoutDAOImpl layoutDAOImpl = new LayoutDAOImpl();
    private static final ClubDAOImpl clubDAOImpl = new ClubDAOImpl();
    private static final ProcessDAOImpl processDAOImpl = new ProcessDAOImpl();
    private static final ProviderDAOImpl providerDAOImpl = new ProviderDAOImpl();
    private static final AdjustOrderDAOImpl adjustOrderDAOImpl = new AdjustOrderDAOImpl();
    private static final AdjustProcessDAOImpl adjustProcessDAOImpl = new AdjustProcessDAOImpl();
    private static final SnapshotDaoImpl snapshotDaoImpl = new SnapshotDaoImpl();

    public static UsersDAOImpl getUserDAOInstance() {
        return usersDAOImpl;
    }

    public static OrdersDAOImpl getOrdersDAOInstance() {
        return ordersDAOImpl;
    }

    public static AssisantprocessDAOImpl getAssisantprocessDAOInstance() {
        return assisantprocessDAOimpl;
    }

    public static GroupResourceDAOImpl getGroupResourceInstance() {
        return groupResourceDAOImpl;
    }

    public static ResourceDAOImpl getResourceInstance() {
        return resourceDAOImpl;
    }

    public static ShiftDAOImpl getShiftInstance() {
        return shiftDAOImpl;
    }

    public static SiteDAOImpl getSiteInstance() {
        return siteDAOImpl;
    }

    public static TyperescourceDAOImpl getTyperescourceInstance() {
        return typerescourceDAOImpl;
    }

    public static ScheduleDAOImpl getScheduleDAOImplInstance() {
        return scheduleDAOImpl;
    }

    public static LayoutDAOImpl getLayoutDAOImplInstance() {
        return layoutDAOImpl;
    }

    public static ClubDAOImpl getClubDAOImplInstance() {
        return clubDAOImpl;
    }

    public static ProcessDAOImpl getProcessDAOImplInstance() {
        return processDAOImpl;
    }

    public static ProviderDAOImpl getProviderDAOImplInstance() {
        return providerDAOImpl;
    }

    public static AdjustOrderDAOImpl getAdjustOrderDAOImplInstance() {
        return adjustOrderDAOImpl;
    }

    public static AdjustProcessDAOImpl getAdjustProcessDAOImplInstance() {
        return adjustProcessDAOImpl;
    }

    public static AdjustDeviceDAOImpl getAdjustDeviceDAOImplInstance() {
        return new AdjustDeviceDAOImpl();
    }

    public static SnapshotDaoImpl getSnapshotDaoImplInstance() {
        return snapshotDaoImpl;
    }
}
