package com.rengu.entity;

import java.util.Set;

public class RG_ScheduleEntity {

    private String id;

    //基础信息
    private String name;
    private String scheduleTime;        //排程日期
    private String startCalcTime;        //计算开始日期
    private String endCalcTime;            //计算结束日期
    private int state;                    //排程状态“下发APS”“APS计算中”“APS计算完成”“交互优化完成”“已下发MES”
    private String progress;            //完成进度

    private int scheduleWindow;            //排程时间窗
    private int rollTime;                //滚动周期

    //APS排程参数
    private String apsStartTime;        //优化项目开始时间
    private String apsEndTime;            //优化项目结束时间
    private String apsModel;            //排程模式，“正向”“反向”
    private String apsObj;                //优化目标，可取值“超时总量”“目标资源数”“作业跨度”

    private RG_LayoutEntity layout;                    //布局
    private Set<RG_OrderEntity> orders;                //订单
    private Set<RG_ResourceEntity> resources;        //资源
    private Set<RG_GroupresourceEntity> groups;        //工组
    private Set<RG_SiteEntity> sites;                //工位

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getScheduleWindow() {
        return scheduleWindow;
    }

    public void setScheduleWindow(int scheduleWindow) {
        this.scheduleWindow = scheduleWindow;
    }

    public int getRollTime() {
        return rollTime;
    }

    public void setRollTime(int rollTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ScheduleEntity that = (RG_ScheduleEntity) o;

        if (state != that.state) return false;
        if (scheduleWindow != that.scheduleWindow) return false;
        if (rollTime != that.rollTime) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (scheduleTime != null ? !scheduleTime.equals(that.scheduleTime) : that.scheduleTime != null) return false;
        if (startCalcTime != null ? !startCalcTime.equals(that.startCalcTime) : that.startCalcTime != null)
            return false;
        if (endCalcTime != null ? !endCalcTime.equals(that.endCalcTime) : that.endCalcTime != null) return false;
        if (progress != null ? !progress.equals(that.progress) : that.progress != null) return false;
        if (apsStartTime != null ? !apsStartTime.equals(that.apsStartTime) : that.apsStartTime != null) return false;
        if (apsEndTime != null ? !apsEndTime.equals(that.apsEndTime) : that.apsEndTime != null) return false;
        if (apsModel != null ? !apsModel.equals(that.apsModel) : that.apsModel != null) return false;
        if (apsObj != null ? !apsObj.equals(that.apsObj) : that.apsObj != null) return false;
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
        result = 31 * result + state;
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + scheduleWindow;
        result = 31 * result + rollTime;
        result = 31 * result + (apsStartTime != null ? apsStartTime.hashCode() : 0);
        result = 31 * result + (apsEndTime != null ? apsEndTime.hashCode() : 0);
        result = 31 * result + (apsModel != null ? apsModel.hashCode() : 0);
        result = 31 * result + (apsObj != null ? apsObj.hashCode() : 0);
        result = 31 * result + (layout != null ? layout.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (resources != null ? resources.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        result = 31 * result + (sites != null ? sites.hashCode() : 0);
        return result;
    }
}
