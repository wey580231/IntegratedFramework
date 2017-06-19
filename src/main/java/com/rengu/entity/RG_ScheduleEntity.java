package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_ScheduleEntity {

    public static final int APS_DISPATCH = 0;           //“下发APS”
    public static final int APS_COMPUTE = 1;            //APS计算中
    public static final int APS_SUCCESS = 2;            //APS计算成功
    public static final int APS_FAIL = 3;               //APS计算失败
    public static final int APS_ADJUST = 4;             //APS优化完成
    public static final int MES_DISPATCH = 5;           //已下发MES

    public static final int ERROR_SUCCESS = 10;         //故障计算完成
    public static final int ERROR_ADJUST = 11;          //APS优化完成
    public static final int ERROR_FAIL = 12  ;          //故障处理失败
    public static final int ERROR_MES_DISPTATCH = 12;   //故障应急后下发MES

    private String id;

    //基础信息
    private String name;
    private String scheduleTime;          //排程日期
    private String startCalcTime;         //计算开始日期
    private String endCalcTime;           //计算结束日期
    private Integer state;                //排程状态,参考本类的静态变量
    private String progress;              //完成进度

    private Integer scheduleWindow;       //排程时间窗
    private Integer rollTime;             //滚动周期

    //APS排程参数
    private String apsStartTime;          //优化项目开始时间
    private String apsEndTime;            //优化项目结束时间
    private String apsModel;              //排程模式，“正向”“反向”
    private String apsObj;                //优化目标，可取值“超时总量”“目标资源数”“作业跨度”

    private String apsFlag;               //用于排程记录和aps结果之间的纽带，aps计算完成后，根据此标识找到记录，并更新状态。

    private RG_LayoutEntity layout;                    //布局
    private Set<RG_OrderEntity> orders;                //订单
    private Set<RG_ResourceEntity> resources;          //资源
    private Set<RG_GroupresourceEntity> groups;        //工组
    private Set<RG_SiteEntity> sites;                  //工位
    private RG_SnapshotNodeEntity snapshot;            //排程快照

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getStartCalcTime() {
        return startCalcTime;
    }

    public void setStartCalcTime(String startCalcTime) {
        this.startCalcTime = startCalcTime;
    }

    public String getEndCalcTime() {
        return endCalcTime;
    }

    public void setEndCalcTime(String endCalcTime) {
        this.endCalcTime = endCalcTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getScheduleWindow() {
        return scheduleWindow;
    }

    public void setScheduleWindow(Integer scheduleWindow) {
        this.scheduleWindow = scheduleWindow;
    }

    public Integer getRollTime() {
        return rollTime;
    }

    public void setRollTime(Integer rollTime) {
        this.rollTime = rollTime;
    }

    public String getApsStartTime() {
        return apsStartTime;
    }

    public void setApsStartTime(String apsStartTime) {
        this.apsStartTime = apsStartTime;
    }

    public String getApsEndTime() {
        return apsEndTime;
    }

    public void setApsEndTime(String apsEndTime) {
        this.apsEndTime = apsEndTime;
    }

    public String getApsModel() {
        return apsModel;
    }

    public void setApsModel(String apsModel) {
        this.apsModel = apsModel;
    }

    public String getApsObj() {
        return apsObj;
    }

    public void setApsObj(String apsObj) {
        this.apsObj = apsObj;
    }

    public String getApsFlag() {
        return apsFlag;
    }

    public void setApsFlag(String apsFlag) {
        this.apsFlag = apsFlag;
    }

    public RG_LayoutEntity getLayout() {
        return layout;
    }

    public void setLayout(RG_LayoutEntity layout) {
        this.layout = layout;
    }

    public Set<RG_OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<RG_OrderEntity> orders) {
        this.orders = orders;
    }

    public Set<RG_ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Set<RG_ResourceEntity> resources) {
        this.resources = resources;
    }

    public Set<RG_GroupresourceEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<RG_GroupresourceEntity> groups) {
        this.groups = groups;
    }

    public Set<RG_SiteEntity> getSites() {
        return sites;
    }

    public void setSites(Set<RG_SiteEntity> sites) {
        this.sites = sites;
    }

    public RG_SnapshotNodeEntity getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(RG_SnapshotNodeEntity snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ScheduleEntity that = (RG_ScheduleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (scheduleTime != null ? !scheduleTime.equals(that.scheduleTime) : that.scheduleTime != null) return false;
        if (startCalcTime != null ? !startCalcTime.equals(that.startCalcTime) : that.startCalcTime != null)
            return false;
        if (endCalcTime != null ? !endCalcTime.equals(that.endCalcTime) : that.endCalcTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (progress != null ? !progress.equals(that.progress) : that.progress != null) return false;
        if (scheduleWindow != null ? !scheduleWindow.equals(that.scheduleWindow) : that.scheduleWindow != null)
            return false;
        if (rollTime != null ? !rollTime.equals(that.rollTime) : that.rollTime != null) return false;
        if (apsStartTime != null ? !apsStartTime.equals(that.apsStartTime) : that.apsStartTime != null) return false;
        if (apsEndTime != null ? !apsEndTime.equals(that.apsEndTime) : that.apsEndTime != null) return false;
        if (apsModel != null ? !apsModel.equals(that.apsModel) : that.apsModel != null) return false;
        if (apsObj != null ? !apsObj.equals(that.apsObj) : that.apsObj != null) return false;
        if (apsFlag != null ? !apsFlag.equals(that.apsFlag) : that.apsFlag != null) return false;
        if (layout != null ? !layout.equals(that.layout) : that.layout != null) return false;
        if (orders != null ? !orders.equals(that.orders) : that.orders != null) return false;
        if (resources != null ? !resources.equals(that.resources) : that.resources != null) return false;
        if (groups != null ? !groups.equals(that.groups) : that.groups != null) return false;
        return sites != null ? sites.equals(that.sites) : that.sites == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (scheduleTime != null ? scheduleTime.hashCode() : 0);
        result = 31 * result + (startCalcTime != null ? startCalcTime.hashCode() : 0);
        result = 31 * result + (endCalcTime != null ? endCalcTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + (scheduleWindow != null ? scheduleWindow.hashCode() : 0);
        result = 31 * result + (rollTime != null ? rollTime.hashCode() : 0);
        result = 31 * result + (apsStartTime != null ? apsStartTime.hashCode() : 0);
        result = 31 * result + (apsEndTime != null ? apsEndTime.hashCode() : 0);
        result = 31 * result + (apsModel != null ? apsModel.hashCode() : 0);
        result = 31 * result + (apsObj != null ? apsObj.hashCode() : 0);
        result = 31 * result + (apsFlag != null ? apsFlag.hashCode() : 0);
        result = 31 * result + (layout != null ? layout.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (resources != null ? resources.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        result = 31 * result + (sites != null ? sites.hashCode() : 0);
        return result;
    }
}
