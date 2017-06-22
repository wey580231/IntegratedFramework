package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_EmulateDataEntity {

    private int id;
    private String item;
    private String state;
    private String good;
    private String startLocation;
    private String endLocation;
    private String startTime;
    private String endTime;

    private RG_OrderEntity orderEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public RG_OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(RG_OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_EmulateDataEntity that = (RG_EmulateDataEntity) o;

        if (id != that.id) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (good != null ? !good.equals(that.good) : that.good != null) return false;
        if (startLocation != null ? !startLocation.equals(that.startLocation) : that.startLocation != null)
            return false;
        if (endLocation != null ? !endLocation.equals(that.endLocation) : that.endLocation != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        return endTime != null ? endTime.equals(that.endTime) : that.endTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (good != null ? good.hashCode() : 0);
        result = 31 * result + (startLocation != null ? startLocation.hashCode() : 0);
        result = 31 * result + (endLocation != null ? endLocation.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
