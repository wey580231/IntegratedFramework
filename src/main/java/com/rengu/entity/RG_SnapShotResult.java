package com.rengu.entity;

import java.util.List;

/**
 * Created by XY on 2017/10/16.
 */
public class RG_SnapShotResult {
    private String id;
    private long span;
    private int delayOrder;
    private long delaySum;
    private List<RG_ResourceRateEntity> useRate;
    private int changeNum;
    private int overSpan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSpan() {
        return span;
    }

    public void setSpan(long span) {
        this.span = span;
    }

    public int getDelayOrder() {
        return delayOrder;
    }

    public void setDelayOrder(int delayOrder) {
        this.delayOrder = delayOrder;
    }

    public long getDelaySum() {
        return delaySum;
    }

    public void setDelaySum(long delaySum) {
        this.delaySum = delaySum;
    }

    public List<RG_ResourceRateEntity> getUseRate() {
        return useRate;
    }

    public void setUseRate(List<RG_ResourceRateEntity> useRate) {
        this.useRate = useRate;
    }

    public int getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(int changeNum) {
        this.changeNum = changeNum;
    }

    public int getOverSpan() {
        return overSpan;
    }

    public void setOverSpan(int overSpan) {
        this.overSpan = overSpan;
    }
}
