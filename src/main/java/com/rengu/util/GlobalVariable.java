package com.rengu.util;

/**
 * 全局变量保存操作集合
 * Created by wey580231 on 2017/6/16.
 */
public class GlobalVariable {
    //TODO 待添加实现对多用户隔离
    public static String latestScheduleId = "1";       //最新排程ID信息，在新建排程后，自动更新此值。
    public static String CurrScheduleId;         //当前对应的排程记录,防止APS调用reply接口多次，在第一次调用后置为
    public static String RootSnapshotId;         //当前快照的根节点ID，创建新schedule排程时更新
    public static String MiddleSnapshotId;       //快照树第二层节点ID，创建schedule或紧急排程时更新，用于在接收aps reply后根据ID创建bottom节点
    public static String BottomSnapshotId;       //快照树第三层节点ID

    public static boolean IsErrorSchedule;       //是否为应急排程
    public static int ApsReplyCount;             //aps在当前MiddleSnapshotId节点下返回结果的次数(区分是否为优化结果)
}
