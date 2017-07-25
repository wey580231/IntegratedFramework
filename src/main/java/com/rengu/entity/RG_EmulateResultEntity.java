package com.rengu.entity;

/**
 * Created by wey580231 on 2017/7/7.
 */
public class RG_EmulateResultEntity {

    private int id;
    private String task;                            //任务号
    private int startTime;                          //开始时间
    private int endTime;                            //结束时间
    private String goods;                           //货物
    private String site;                            //地点

    private RG_OrderEntity orderEntity;
    private RG_SnapshotNodeEntity snapshotNodeEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public RG_OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(RG_OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public RG_SnapshotNodeEntity getSnapshotNodeEntity() {
        return snapshotNodeEntity;
    }

    public void setSnapshotNodeEntity(RG_SnapshotNodeEntity snapshotNodeEntity) {
        this.snapshotNodeEntity = snapshotNodeEntity;
    }
}
