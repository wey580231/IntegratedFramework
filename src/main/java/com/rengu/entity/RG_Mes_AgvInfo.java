package com.rengu.entity;

import java.util.Date;

/**agv信息
 * Created by wey580231 on 2017/7/6.
 */
public class RG_Mes_AgvInfo {
    private int id;
    private String agvId;                           //agv编号
    private boolean state;                          //状态
    private String goodsDesc;                       //物料描述
    private String idOrder;                         //订单编号
    private float remainPower;                      //剩余电量
    private Date reportTime;                        //上报时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgvId() {
        return agvId;
    }

    public void setAgvId(String agvId) {
        this.agvId = agvId;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public float getRemainPower() {
        return remainPower;
    }

    public void setRemainPower(float remainPower) {
        this.remainPower = remainPower;
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

        RG_Mes_AgvInfo that = (RG_Mes_AgvInfo) o;

        if (id != that.id) return false;
        if (state != that.state) return false;
        if (Float.compare(that.remainPower, remainPower) != 0) return false;
        if (agvId != null ? !agvId.equals(that.agvId) : that.agvId != null) return false;
        if (goodsDesc != null ? !goodsDesc.equals(that.goodsDesc) : that.goodsDesc != null) return false;
        if (idOrder != null ? !idOrder.equals(that.idOrder) : that.idOrder != null) return false;
        return reportTime != null ? reportTime.equals(that.reportTime) : that.reportTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (agvId != null ? agvId.hashCode() : 0);
        result = 31 * result + (state ? 1 : 0);
        result = 31 * result + (goodsDesc != null ? goodsDesc.hashCode() : 0);
        result = 31 * result + (idOrder != null ? idOrder.hashCode() : 0);
        result = 31 * result + (remainPower != +0.0f ? Float.floatToIntBits(remainPower) : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        return result;
    }
}
