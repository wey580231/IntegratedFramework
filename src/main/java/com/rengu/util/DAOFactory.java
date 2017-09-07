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
    private static final LayoutDetailDAOImpl layoutDetailDAOImpl = new LayoutDetailDAOImpl();

    private static final ClubDAOImpl clubDAOImpl = new ClubDAOImpl();
    private static final ProcessDAOImpl processDAOImpl = new ProcessDAOImpl();

    private static final ProviderDAOImpl providerDAOImpl = new ProviderDAOImpl();
    private static final AdjustOrderDAOImpl adjustOrderDAOImpl = new AdjustOrderDAOImpl();
    private static final AdjustProcessDAOImpl adjustProcessDAOImpl = new AdjustProcessDAOImpl();
    private static final SnapshotDaoImpl snapshotDaoImpl = new SnapshotDaoImpl();
    private static final PlanDAOImpl planDAOImpl = new PlanDAOImpl();
    private static final ProductDAOImpl productDAOImpl = new ProductDAOImpl();
    private static final EventLogDAOImpl eventLogDAOImpl = new EventLogDAOImpl();
    private static final AdjustLayoutDAOImpl adjustLayoutDAOImpl = new AdjustLayoutDAOImpl();
    private static final DistanceDAOImpl distanceDAOImpl = new DistanceDAOImpl();
    private static final UserConfigDAOImpl userConfigDAOImpl = new UserConfigDAOImpl();
    private static final TaskDAOImpl taskDAOImpl = new TaskDAOImpl();

    private static final CarryDAOImpl carryDAOImpl = new CarryDAOImpl();
    private static final AssemblyCarryInfoDAOImpl assemblyCarryInfoDAOImpl = new AssemblyCarryInfoDAOImpl();
    private static final AssemblyCenterInfoDAOImpl assemblyCenterInfoDAOImpl = new AssemblyCenterInfoDAOImpl();
    private static final DeportInfoDAOImpl deportInfoDAOImpl = new DeportInfoDAOImpl();
    private static final AGVInfoDAOImpl agvInfoDAOImpl = new AGVInfoDAOImpl();
    private static final ConfigDAOImpl configDAOImpl = new ConfigDAOImpl();
    private static final ResourceTyperesourceDAOImpl resourceTypeResourceDAOImpl = new ResourceTyperesourceDAOImpl();

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

    public static LayoutDetailDAOImpl getLayoutDetailDAOImplInstance() {
        return layoutDetailDAOImpl;
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

    public static PlanDAOImpl getPlanDAOImplInstance() {
        return planDAOImpl;
    }

    public static ProductDAOImpl getProductDAOImplInstance() {
        return productDAOImpl;
    }

    public static EventLogDAOImpl getEventLogDAOImplInstance() {
        return eventLogDAOImpl;
    }

    public static AdjustLayoutDAOImpl getAdjustLayoutDAOImplInstance() {
        return adjustLayoutDAOImpl;
    }

    public static DistanceDAOImpl getDistanceDAOImplInstance() {
        return distanceDAOImpl;
    }

    public static UserConfigDAOImpl getUserConfigDAOImplInstance() {
        return userConfigDAOImpl;
    }

    public static TaskDAOImpl getTaskDAOImplInstance() {
        return taskDAOImpl;
    }

    public static CarryDAOImpl getCarryDAOImplInstance() {
        return carryDAOImpl;
    }

    public static AssemblyCarryInfoDAOImpl getAssemblyCarryInfoDAOImplInstance() {
        return assemblyCarryInfoDAOImpl;
    }

    public static AssemblyCenterInfoDAOImpl getAssemblyCenterInfoDAOImplInstance() {
        return assemblyCenterInfoDAOImpl;
    }

    public static DeportInfoDAOImpl getDeportInfoDAOImplInstance() {
        return deportInfoDAOImpl;
    }

    public static AGVInfoDAOImpl getAGVInfoDAOImplInstance() {
        return agvInfoDAOImpl;
    }

    public static ConfigDAOImpl getConfigDAOImplInstance() {
        return configDAOImpl;
    }

    public static ResourceTyperesourceDAOImpl getResourceTyperesourceInstance() {
        return resourceTypeResourceDAOImpl;
    }



}