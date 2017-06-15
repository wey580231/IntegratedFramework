package com.rengu.entity;

/**设备资源调整
 * Created by wey580231 on 2017/6/15.
 */
public class RG_AdjustDeviceEntity {

    private String id;                  //UUID
    private String orderId;             //订单编码
    private String reportTime;          //上报时间
    private String resoureId;          //资源编码
    private String origin;              //异常来源，MES、手工模拟
    private Integer state;              //异常状态，参照ErrorState类

    private String cancelTime;          //撤销时间
    private String latestCancelTime;    //最晚撤销时间

    private String unavailableStartTime;    //资源不可用开始间
    private String unavailableEndTime;      //资源不可用结束时间
    private String unavailableStartDate;    //资源不可用开始段日期
    private String unavailableEndDate;      //资源不可用段结束日期

    private RG_OrderEntity orderEntity;
    private RG_ResourceEntity resourceEntity;

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

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_AdjustDeviceEntity that = (RG_AdjustDeviceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
        if (resoureId != null ? !resoureId.equals(that.resoureId) : that.resoureId != null) return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (cancelTime != null ? !cancelTime.equals(that.cancelTime) : that.cancelTime != null) return false;
        if (latestCancelTime != null ? !latestCancelTime.equals(that.latestCancelTime) : that.latestCancelTime != null)
            return false;
        if (unavailableStartTime != null ? !unavailableStartTime.equals(that.unavailableStartTime) : that.unavailableStartTime != null)
            return false;
        if (unavailableEndTime != null ? !unavailableEndTime.equals(that.unavailableEndTime) : that.unavailableEndTime != null)
            return false;
        if (unavailableStartDate != null ? !unavailableStartDate.equals(that.unavailableStartDate) : that.unavailableStartDate != null)
            return false;
        if (unavailableEndDate != null ? !unavailableEndDate.equals(that.unavailableEndDate) : that.unavailableEndDate != null)
            return false;
        if (orderEntity != null ? !orderEntity.equals(that.orderEntity) : that.orderEntity != null) return false;
        return resourceEntity != null ? resourceEntity.equals(that.resourceEntity) : that.resourceEntity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (resoureId != null ? resoureId.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (cancelTime != null ? cancelTime.hashCode() : 0);
        result = 31 * result + (latestCancelTime != null ? latestCancelTime.hashCode() : 0);
        result = 31 * result + (unavailableStartTime != null ? unavailableStartTime.hashCode() : 0);
        result = 31 * result + (unavailableEndTime != null ? unavailableEndTime.hashCode() : 0);
        result = 31 * result + (unavailableStartDate != null ? unavailableStartDate.hashCode() : 0);
        result = 31 * result + (unavailableEndDate != null ? unavailableEndDate.hashCode() : 0);
        result = 31 * result + (orderEntity != null ? orderEntity.hashCode() : 0);
        result = 31 * result + (resourceEntity != null ? resourceEntity.hashCode() : 0);
        return result;
    }
}
