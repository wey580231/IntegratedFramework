package com.rengu.entity;

import java.util.Set;

public class RG_ScheduleEntity {

    private int id;

    //基础信息
    private String name;
    private String scheduleTime;		//排程日期
    private String startCalcTime;		//计算开始日期
    private String endCalcTime;			//计算结束日期
    private int state;					//排程状态“下发APS”“APS计算中”“APS计算完成”“交互优化完成”“已下发MES”
    private String progress;			//完成进度

    private int scheduleWindow;			//排程时间窗
    private int rollTime;				//滚动周期

    //APS排程参数
    private String apsStartTime;		//优化项目开始时间
    private String apsEndTime;			//优化项目结束时间
    private String apsModel;			//排程模式，“正向”“反向”
    private String apsObj;				//优化目标，可取值“超时总量”“目标资源数”“作业跨度”

    private RG_LayoutEntity layout;					//布局
    private Set<RG_OrderEntity> orders;				//订单
    private Set<RG_ResourceEntity> resources;		//资源
    private Set<RG_GroupresourceEntity> groups;		//工组
    private Set<RG_SiteEntity> sites;				//工位

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((apsEndTime == null) ? 0 : apsEndTime.hashCode());
        result = prime * result
                + ((apsModel == null) ? 0 : apsModel.hashCode());
        result = prime * result + ((apsObj == null) ? 0 : apsObj.hashCode());
        result = prime * result
                + ((apsStartTime == null) ? 0 : apsStartTime.hashCode());
        result = prime * result
                + ((endCalcTime == null) ? 0 : endCalcTime.hashCode());
        result = prime * result + ((groups == null) ? 0 : groups.hashCode());
        result = prime * result + id;
        result = prime * result + ((layout == null) ? 0 : layout.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((orders == null) ? 0 : orders.hashCode());
        result = prime * result
                + ((progress == null) ? 0 : progress.hashCode());
        result = prime * result
                + ((resources == null) ? 0 : resources.hashCode());
        result = prime * result + rollTime;
        result = prime * result
                + ((scheduleTime == null) ? 0 : scheduleTime.hashCode());
        result = prime * result + scheduleWindow;
        result = prime * result + ((sites == null) ? 0 : sites.hashCode());
        result = prime * result
                + ((startCalcTime == null) ? 0 : startCalcTime.hashCode());
        result = prime * result + state;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RG_ScheduleEntity other = (RG_ScheduleEntity) obj;
        if (apsEndTime == null) {
            if (other.apsEndTime != null)
                return false;
        } else if (!apsEndTime.equals(other.apsEndTime))
            return false;
        if (apsModel == null) {
            if (other.apsModel != null)
                return false;
        } else if (!apsModel.equals(other.apsModel))
            return false;
        if (apsObj == null) {
            if (other.apsObj != null)
                return false;
        } else if (!apsObj.equals(other.apsObj))
            return false;
        if (apsStartTime == null) {
            if (other.apsStartTime != null)
                return false;
        } else if (!apsStartTime.equals(other.apsStartTime))
            return false;
        if (endCalcTime == null) {
            if (other.endCalcTime != null)
                return false;
        } else if (!endCalcTime.equals(other.endCalcTime))
            return false;
        if (groups == null) {
            if (other.groups != null)
                return false;
        } else if (!groups.equals(other.groups))
            return false;
        if (id != other.id)
            return false;
        if (layout == null) {
            if (other.layout != null)
                return false;
        } else if (!layout.equals(other.layout))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (orders == null) {
            if (other.orders != null)
                return false;
        } else if (!orders.equals(other.orders))
            return false;
        if (progress == null) {
            if (other.progress != null)
                return false;
        } else if (!progress.equals(other.progress))
            return false;
        if (resources == null) {
            if (other.resources != null)
                return false;
        } else if (!resources.equals(other.resources))
            return false;
        if (rollTime != other.rollTime)
            return false;
        if (scheduleTime == null) {
            if (other.scheduleTime != null)
                return false;
        } else if (!scheduleTime.equals(other.scheduleTime))
            return false;
        if (scheduleWindow != other.scheduleWindow)
            return false;
        if (sites == null) {
            if (other.sites != null)
                return false;
        } else if (!sites.equals(other.sites))
            return false;
        if (startCalcTime == null) {
            if (other.startCalcTime != null)
                return false;
        } else if (!startCalcTime.equals(other.startCalcTime))
            return false;
        if (state != other.state)
            return false;
        return true;
    }

}
