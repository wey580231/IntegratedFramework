package com.rengu.entity;

import java.util.Date;

/**立体仓库信息
 * Created by wey580231 on 2017/7/6.
 */
public class RG_Mes_DeportInfo {

    private String id;
    private String deportId;                        //货架编号
    private String deportName;                      //货架中存储货物名称
    private Integer stock;                          //数量
    private Integer totalPlace;                     //总存储位
    private Integer freePlace;                      //剩余存储位
    private Date reportTime;                        //上报时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Integer getTotalPlace() {
        return totalPlace;
    }

    public void setTotalPlace(Integer totalPlace) {
        this.totalPlace = totalPlace;
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
}
