package com.rengu.entity;

/**
 * Created by hanchangming on 2017/6/16.
 */
public class RG_AdjustProcessEntity {
    private String id;
    private String reportTime;
    private String idTask;
    private String t1Task;
    private String appointIdResource;
    private String appointTaskTime;

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
        if (orderEntity != null ? !orderEntity.equals(that.orderEntity) : that.orderEntity != null) return false;
        return resourceEntity != null ? resourceEntity.equals(that.resourceEntity) : that.resourceEntity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (idTask != null ? idTask.hashCode() : 0);
        result = 31 * result + (t1Task != null ? t1Task.hashCode() : 0);
        result = 31 * result + (appointIdResource != null ? appointIdResource.hashCode() : 0);
        result = 31 * result + (appointTaskTime != null ? appointTaskTime.hashCode() : 0);
        result = 31 * result + (orderEntity != null ? orderEntity.hashCode() : 0);
        result = 31 * result + (resourceEntity != null ? resourceEntity.hashCode() : 0);
        return result;
    }
}
