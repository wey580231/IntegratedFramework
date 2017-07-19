package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by hanchangming on 2017/6/16.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_AdjustOrderEntity {
    private String id;

    private RG_OrderEntity ord;         //紧急插单信息

    private String origin;              //异常来源，MES、手工模拟
    private Integer state;              //异常状态，参照ErrorState类
    private Date reportTime;            //故障上报时间
    private Date processTime;           //处理时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RG_OrderEntity getOrd() {
        return ord;
    }

    public void setOrd(RG_OrderEntity ord) {
        this.ord = ord;
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
}
