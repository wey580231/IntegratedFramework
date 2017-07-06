package com.rengu.entity;

import java.util.Date;

/**
 * Created by wey580231 on 2017/7/6.
 */
public class RG_Mes_AssemblyCenterInfo {

    private int id;
    private String carryId;                             //搬运机器人编号
    private boolean state;                              //正常/不正常
    private String jobDesc;                             //正在执行的搬运工作
    private String idOrder;                             //订单编号
    private Date reportTime;                            //上报时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarryId() {
        return carryId;
    }

    public void setCarryId(String carryId) {
        this.carryId = carryId;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_Mes_AssemblyCenterInfo that = (RG_Mes_AssemblyCenterInfo) o;

        if (id != that.id) return false;
        if (state != that.state) return false;
        if (carryId != null ? !carryId.equals(that.carryId) : that.carryId != null) return false;
        if (jobDesc != null ? !jobDesc.equals(that.jobDesc) : that.jobDesc != null) return false;
        if (idOrder != null ? !idOrder.equals(that.idOrder) : that.idOrder != null) return false;
        return reportTime != null ? reportTime.equals(that.reportTime) : that.reportTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (carryId != null ? carryId.hashCode() : 0);
        result = 31 * result + (state ? 1 : 0);
        result = 31 * result + (jobDesc != null ? jobDesc.hashCode() : 0);
        result = 31 * result + (idOrder != null ? idOrder.hashCode() : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        return result;
    }
}
