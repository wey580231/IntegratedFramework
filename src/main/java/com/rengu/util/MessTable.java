package com.rengu.util;

/**
 * mes表信息
 * Created by wey580231 on 2017/7/6.
 */
public class MessTable {

    //基础信息
    public static String MES_PRODUCT = "product";                                 //产品表
    public static String MES_RESOURCE = "resource";                               //资源表
    public static String MES_SITE = "site";                                       //地点表
    public static String MES_SITE_DISTANCE = "siteDistance";                      //地点-地点距离表
    public static String MES_PROCESS = "process";                                 //工艺表

    //实时信息
    public static String MES_REAL_INFO = "realData";                            //实时信息
    public static String MES_DEPORT_INFO = "deportInfo";                          //立体仓库信息
    public static String MES_CARRY_INFO = "carryInfo";                            //仓库搬运机器人信息
    public static String MES_AGV_INFO = "agvInfo";                                //AGV信息
    public static String MES_ASSEMBLYCARRY_INFO = "assemblyCarryInfo";            //装配线搬运机器人信息
    public static String MES_ASSEMBLYCENTER_INFO = "assemblyCenterInfo";          //智能装配中心信息

    public static String MES_MAN_MANCHINE_INFO = "manMachineInfo";                //人机协作平台
    public static String MES_DEGREE_INFO = "degreeAccuracyInfo";                  //精度检测
    public static String MES_MODEL_TEST_INFO = "modelTestInfo";                   //模态检测
    public static String MES_ELECTRICITY_INFO = "electricityInfo";                //电性能检测

    public static String MES_DEVICESTATE_INFO = "deviceStateInfo";                //设备状态信息
    public static String MES_ORDERSTATE_INFO = "orderStateInfo";                  //订单执行信息
    public static String MES_INSTRUCT_INFO = "instructInfo";                      //工序指令信息
    public static String MES_ADJUSTDEVICE_INFO = "adjustDeviceInfo";              //设备调整

    //框架向mes提供信息
    public static String MES_PLAN_INFO = "planInfo";                              //生产计划信息
    public static String MES_TRANSPORT_INFO = "transportInfo";                    //生产运输信息
    public static String MES_ORDER_INFO = "orderInfo";                            //订单信息
}
