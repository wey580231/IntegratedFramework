package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by hanchangming on 2017/6/16.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_AdjustProcessEntity {
    private String id;
    private Date reportTime;                    //故障上报时间
    private String idTask;                      //选中工序编码
    private String idJob;                       //工序作业编码
    private String idOrder;                     //工序订单编码
    private String originalResource;            //工序原始选用资源编码
    private Date originalStartTime;             //工序原始开始时间
    private String appointResource;             //工序指定资源编码
    private Date appointStartTime;              //工序指定开始时间

    private String origin;                      //异常来源，MES、手工模拟
    private Integer state;                      //异常状态，参照ErrorState类

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getOriginalResource() {
        return originalResource;
    }

    public void setOriginalResource(String originalResource) {
        this.originalResource = originalResource;
    }

    public Date getOriginalStartTime() {
        return originalStartTime;
    }

    public void setOriginalStartTime(Date originalStartTime) {
        this.originalStartTime = originalStartTime;
    }

    public String getAppointResource() {
        return appointResource;
    }

    public void setAppointResource(String appointResource) {
        this.appointResource = appointResource;
    }

    public Date getAppointStartTime() {
        return appointStartTime;
    }

    public void setAppointStartTime(Date appointStartTime) {
        this.appointStartTime = appointStartTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_AdjustProcessEntity that = (RG_AdjustProcessEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
        if (idTask != null ? !idTask.equals(that.idTask) : that.idTask != null) return false;
        if (idJob != null ? !idJob.equals(that.idJob) : that.idJob != null) return false;
        if (idOrder != null ? !idOrder.equals(that.idOrder) : that.idOrder != null) return false;
        if (originalResource != null ? !originalResource.equals(that.originalResource) : that.originalResource != null)
            return false;
        if (originalStartTime != null ? !originalStartTime.equals(that.originalStartTime) : that.originalStartTime != null)
            return false;
        if (appointResource != null ? !appointResource.equals(that.appointResource) : that.appointResource != null)
            return false;
        if (appointStartTime != null ? !appointStartTime.equals(that.appointStartTime) : that.appointStartTime != null)
            return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (idTask != null ? idTask.hashCode() : 0);
        result = 31 * result + (idJob != null ? idJob.hashCode() : 0);
        result = 31 * result + (idOrder != null ? idOrder.hashCode() : 0);
        result = 31 * result + (originalResource != null ? originalResource.hashCode() : 0);
        result = 31 * result + (originalStartTime != null ? originalStartTime.hashCode() : 0);
        result = 31 * result + (appointResource != null ? appointResource.hashCode() : 0);
        result = 31 * result + (appointStartTime != null ? appointStartTime.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
