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
    private Date processTime;                   //处理时间
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

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
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
}
