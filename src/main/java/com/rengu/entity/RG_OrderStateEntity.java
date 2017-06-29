package com.rengu.entity;

import java.util.Date;

/**
 * Created by wey580231 on 2017/6/28.
 */
public class RG_OrderStateEntity {
    private int id;

    private String idTask;                          //工序编码
    private String nameTask;                        //工序名称
    private String planDevice;                      //派工设备
    private short planCount;                        //计划数量
    private Date planStartTime;                     //计划开工时间
    private Date planFinishTime;                    //计划完工时间
    private String actualDispatchDevice;            //实际派工设备
    private short actualFinishCount;                //实际完工量
    private Date currTime;                          //当前时间
    private boolean finished;                       //是否已经完工
    private float finishPercent;                    //计划达成率

    private RG_OrderEntity orderEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getPlanDevice() {
        return planDevice;
    }

    public void setPlanDevice(String planDevice) {
        this.planDevice = planDevice;
    }

    public short getPlanCount() {
        return planCount;
    }

    public void setPlanCount(short planCount) {
        this.planCount = planCount;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanFinishTime() {
        return planFinishTime;
    }

    public void setPlanFinishTime(Date planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    public String getActualDispatchDevice() {
        return actualDispatchDevice;
    }

    public void setActualDispatchDevice(String actualDispatchDevice) {
        this.actualDispatchDevice = actualDispatchDevice;
    }

    public short getActualFinishCount() {
        return actualFinishCount;
    }

    public void setActualFinishCount(short actualFinishCount) {
        this.actualFinishCount = actualFinishCount;
    }

    public Date getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Date currTime) {
        this.currTime = currTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public float getFinishPercent() {
        return finishPercent;
    }

    public void setFinishPercent(float finishPercent) {
        this.finishPercent = finishPercent;
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

        RG_OrderStateEntity that = (RG_OrderStateEntity) o;

        if (id != that.id) return false;
        if (planCount != that.planCount) return false;
        if (actualFinishCount != that.actualFinishCount) return false;
        if (finished != that.finished) return false;
        if (Float.compare(that.finishPercent, finishPercent) != 0) return false;
        if (idTask != null ? !idTask.equals(that.idTask) : that.idTask != null) return false;
        if (nameTask != null ? !nameTask.equals(that.nameTask) : that.nameTask != null) return false;
        if (planDevice != null ? !planDevice.equals(that.planDevice) : that.planDevice != null) return false;
        if (planStartTime != null ? !planStartTime.equals(that.planStartTime) : that.planStartTime != null)
            return false;
        if (planFinishTime != null ? !planFinishTime.equals(that.planFinishTime) : that.planFinishTime != null)
            return false;
        if (actualDispatchDevice != null ? !actualDispatchDevice.equals(that.actualDispatchDevice) : that.actualDispatchDevice != null)
            return false;
        return currTime != null ? currTime.equals(that.currTime) : that.currTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idTask != null ? idTask.hashCode() : 0);
        result = 31 * result + (nameTask != null ? nameTask.hashCode() : 0);
        result = 31 * result + (planDevice != null ? planDevice.hashCode() : 0);
        result = 31 * result + (int) planCount;
        result = 31 * result + (planStartTime != null ? planStartTime.hashCode() : 0);
        result = 31 * result + (planFinishTime != null ? planFinishTime.hashCode() : 0);
        result = 31 * result + (actualDispatchDevice != null ? actualDispatchDevice.hashCode() : 0);
        result = 31 * result + (int) actualFinishCount;
        result = 31 * result + (currTime != null ? currTime.hashCode() : 0);
        result = 31 * result + (finished ? 1 : 0);
        result = 31 * result + (finishPercent != +0.0f ? Float.floatToIntBits(finishPercent) : 0);
        return result;
    }
}
