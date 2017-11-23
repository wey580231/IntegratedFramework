package com.rengu.entity;

/**
 * 每个用户的配置信息
 * Created by wey580231 on 2017/6/22.
 */
public class RG_UserConfigEntity {

    private Integer id;
    private String latestScheduleId;                //最新排程ID信息，在新建排程后，自动更新此值。
    private String currScheduleId;                  //当前对应的排程记录,防止APS调用reply接口多次
    private String rootSnapshotId;                  //当前快照的根节点ID，创建新schedule排程时更新
    private String middleSnapshotId;                //快照树第二层节点ID，创建schedule或紧急排程时更新，用于在接收aps reply后根据ID创建bottom节点
    private String bottomSnapshotId;                //快照树第三层节点ID
    private boolean errorSchedule;                  //是否为紧急排程
    private Integer apsReplyCount;                  //aps在当前MiddleSnapshotId节点下返回结果的次数(区分是否为优化结果)
    private boolean resetApsTable;                  //是否要清空aps表,1为要清空，0为不清空。

    private String errorType;                       //异常处理时的类型
    private String errorId;                         //对应异常的id

    private String dispatchMesSnapshotId;           //记录下发MES的snapshot的id信息
    private String factoryLayoutId;                 //工厂所采用的实际布局

    private String apsCurrSnapshotId;               //aps当前的快照对应bottomId，如果为null或者size=0，则用最新一次排程的bottomId

    private RG_UserEntity user;               //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLatestScheduleId() {
        return latestScheduleId;
    }

    public void setLatestScheduleId(String latestScheduleId) {
        this.latestScheduleId = latestScheduleId;
    }

    public String getCurrScheduleId() {
        return currScheduleId;
    }

    public void setCurrScheduleId(String currScheduleId) {
        this.currScheduleId = currScheduleId;
    }

    public String getRootSnapshotId() {
        return rootSnapshotId;
    }

    public void setRootSnapshotId(String rootSnapshotId) {
        this.rootSnapshotId = rootSnapshotId;
    }

    public String getMiddleSnapshotId() {
        return middleSnapshotId;
    }

    public void setMiddleSnapshotId(String middleSnapshotId) {
        this.middleSnapshotId = middleSnapshotId;
    }

    public String getBottomSnapshotId() {
        return bottomSnapshotId;
    }

    public void setBottomSnapshotId(String bottomSnapshotId) {
        this.bottomSnapshotId = bottomSnapshotId;
    }

    public boolean isErrorSchedule() {
        return errorSchedule;
    }

    public void setErrorSchedule(boolean errorSchedule) {
        this.errorSchedule = errorSchedule;
    }

    public Integer getApsReplyCount() {
        return apsReplyCount;
    }

    public void setApsReplyCount(Integer apsReplyCount) {
        this.apsReplyCount = apsReplyCount;
    }

    public boolean isResetApsTable() {
        return resetApsTable;
    }

    public void setResetApsTable(boolean resetApsTable) {
        this.resetApsTable = resetApsTable;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getDispatchMesSnapshotId() {
        return dispatchMesSnapshotId;
    }

    public void setDispatchMesSnapshotId(String dispatchMesSnapshotId) {
        this.dispatchMesSnapshotId = dispatchMesSnapshotId;
    }

    public String getFactoryLayoutId() {
        return factoryLayoutId;
    }

    public void setFactoryLayoutId(String factoryLayoutId) {
        this.factoryLayoutId = factoryLayoutId;
    }

    public String getApsCurrSnapshotId() {
        return apsCurrSnapshotId;
    }

    public void setApsCurrSnapshotId(String apsCurrSnapshotId) {
        this.apsCurrSnapshotId = apsCurrSnapshotId;
    }

    public RG_UserEntity getUser() {
        return user;
    }

    public void setUser(RG_UserEntity user) {
        this.user = user;
    }
}
