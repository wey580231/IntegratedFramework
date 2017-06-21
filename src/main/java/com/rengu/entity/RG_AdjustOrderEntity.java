package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hanchangming on 2017/6/16.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_AdjustOrderEntity {
    private String id;

    private RG_OrderEntity ord;         //紧急插单信息

    private String origin;              //异常来源，MES、手工模拟
    private Integer state;              //异常状态，参照ErrorState类

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_AdjustOrderEntity that = (RG_AdjustOrderEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
