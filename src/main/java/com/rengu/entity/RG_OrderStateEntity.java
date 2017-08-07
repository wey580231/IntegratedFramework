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
    private Date actualDispatchTime;                //实际派工时间
    private Date actualFinsihTime;                  //实际完工时间
    private short actualFinishCount;                //实际完工量        //(合格+不合格)/计划数  框架计算
    private short unqualifiedCount;                 //不合格品数量
    private short qualifiedCount;                   //合格品数量
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

    public Date getActualDispatchTime() {
        return actualDispatchTime;
    }

    public void setActualDispatchTime(Date actualDispatchTime) {
        this.actualDispatchTime = actualDispatchTime;
    }

    public Date getActualFinsihTime() {
        return actualFinsihTime;
    }

    public void setActualFinsihTime(Date actualFinsihTime) {
        this.actualFinsihTime = actualFinsihTime;
    }

    public short getUnqualifiedCount() {
        return unqualifiedCount;
    }

    public void setUnqualifiedCount(short unqualifiedCount) {
        this.unqualifiedCount = unqualifiedCount;
    }

    public short getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(short qualifiedCount) {
        this.qualifiedCount = qualifiedCount;
    }
}
