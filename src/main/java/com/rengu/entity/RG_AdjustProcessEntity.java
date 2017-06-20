package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hanchangming on 2017/6/16.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_AdjustProcessEntity {
    private String id;
    private String reportTime;
    private String idTask;
    private String t1Task;
    private String appointIdResource;
    private String appointTaskTime;

    private String origin;              //异常来源，MES、手工模拟
    private Integer state;              //异常状态，参照ErrorState类

    private RG_OrderEntity orderEntity;
    private RG_ResourceEntity resourceEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getT1Task() {
        return t1Task;
    }

    public void setT1Task(String t1Task) {
        this.t1Task = t1Task;
    }

    public String getAppointIdResource() {
        return appointIdResource;
    }

    public void setAppointIdResource(String appointIdResource) {
        this.appointIdResource = appointIdResource;
    }

    public String getAppointTaskTime() {
        return appointTaskTime;
    }

    public void setAppointTaskTime(String appointTaskTime) {
        this.appointTaskTime = appointTaskTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public RG_OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(RG_OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public RG_ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(RG_ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_AdjustProcessEntity that = (RG_AdjustProcessEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
        if (idTask != null ? !idTask.equals(that.idTask) : that.idTask != null) return false;
        if (t1Task != null ? !t1Task.equals(that.t1Task) : that.t1Task != null) return false;
        if (appointIdResource != null ? !appointIdResource.equals(that.appointIdResource) : that.appointIdResource != null)
            return false;
        if (appointTaskTime != null ? !appointTaskTime.equals(that.appointTaskTime) : that.appointTaskTime != null)
            return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (idTask != null ? idTask.hashCode() : 0);
        result = 31 * result + (t1Task != null ? t1Task.hashCode() : 0);
        result = 31 * result + (appointIdResource != null ? appointIdResource.hashCode() : 0);
        result = 31 * result + (appointTaskTime != null ? appointTaskTime.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
