package com.rengu.entity;

import java.util.Date;

/**立体仓库信息
 * Created by wey580231 on 2017/7/6.
 */
public class RG_Mes_DeportInfo {

    private int id;
    private String deportId;                        //货架编号
    private String deportName;                      //货架中存储货物名称
    private Integer stock;                          //数量
    private Integer freePlace;                      //剩余存储位
    private Date reportTime;                        //上报时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeportId() {
        return deportId;
    }

    public void setDeportId(String deportId) {
        this.deportId = deportId;
    }

    public String getDeportName() {
        return deportName;
    }

    public void setDeportName(String deportName) {
        this.deportName = deportName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getFreePlace() {
        return freePlace;
    }

    public void setFreePlace(Integer freePlace) {
        this.freePlace = freePlace;
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

        RG_Mes_DeportInfo that = (RG_Mes_DeportInfo) o;

        if (id != that.id) return false;
        if (deportId != null ? !deportId.equals(that.deportId) : that.deportId != null) return false;
        if (deportName != null ? !deportName.equals(that.deportName) : that.deportName != null) return false;
        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;
        if (freePlace != null ? !freePlace.equals(that.freePlace) : that.freePlace != null) return false;
        return reportTime != null ? reportTime.equals(that.reportTime) : that.reportTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (deportId != null ? deportId.hashCode() : 0);
        result = 31 * result + (deportName != null ? deportName.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (freePlace != null ? freePlace.hashCode() : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        return result;
    }
}
