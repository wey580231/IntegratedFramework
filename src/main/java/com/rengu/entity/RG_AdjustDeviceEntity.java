package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**设备资源调整
 * Created by wey580231 on 2017/6/15.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_AdjustDeviceEntity {

    private String id;                  //UUID
    private String orderId;             //订单编码
    private Date reportTime;            //上报时间
    private String resoureId;           //资源编码
    private String origin;              //异常来源，MES、手工模拟
    private Integer state;              //异常状态，参照ErrorState类
    private Date processTime;           //处理时间

    private String cancelTime;          //撤销时间
    private String latestCancelTime;    //最晚撤销时间

    private String unavailableStartTime;    //资源不可用开始间
    private String unavailableEndTime;      //资源不可用结束时间
    private String unavailableStartDate;    //资源不可用开始段日期
    private String unavailableEndDate;      //资源不可用段结束日期

    private RG_OrderEntity orderEntity;
    private RG_ResourceEntity resourceEntity;

    /*public String getIdA() {
        return idA;
    }

    public void setIdA(String idA) {
        this.idA = idA;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getResoureId() {
        return resoureId;
    }

    public void setResoureId(String resoureId) {
        this.resoureId = resoureId;
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

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getLatestCancelTime() {
        return latestCancelTime;
    }

    public void setLatestCancelTime(String latestCancelTime) {
        this.latestCancelTime = latestCancelTime;
    }

    public String getUnavailableStartTime() {
        return unavailableStartTime;
    }

    public void setUnavailableStartTime(String unavailableStartTime) {
        this.unavailableStartTime = unavailableStartTime;
    }

    public String getUnavailableEndTime() {
        return unavailableEndTime;
    }

    public void setUnavailableEndTime(String unavailableEndTime) {
        this.unavailableEndTime = unavailableEndTime;
    }

    public String getUnavailableStartDate() {
        return unavailableStartDate;
    }

    public void setUnavailableStartDate(String unavailableStartDate) {
        this.unavailableStartDate = unavailableStartDate;
    }

    public String getUnavailableEndDate() {
        return unavailableEndDate;
    }

    public void setUnavailableEndDate(String unavailableEndDate) {
        this.unavailableEndDate = unavailableEndDate;
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
}
