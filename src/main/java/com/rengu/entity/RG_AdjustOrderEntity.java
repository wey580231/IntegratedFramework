package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hanchangming on 2017/6/16.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_AdjustOrderEntity {
    private String id;
    private String name;
    private Short quantity;
    private Short priority;
    private String t0;
    private String t1;
    private String t2;
    private RG_ProductEntity productEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    public String getT0() {
        return t0;
    }

    public void setT0(String t0) {
        this.t0 = t0;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public RG_ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(RG_ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_AdjustOrderEntity that = (RG_AdjustOrderEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (t0 != null ? !t0.equals(that.t0) : that.t0 != null) return false;
        if (t1 != null ? !t1.equals(that.t1) : that.t1 != null) return false;
        if (t2 != null ? !t2.equals(that.t2) : that.t2 != null) return false;
        return productEntity != null ? productEntity.equals(that.productEntity) : that.productEntity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (t0 != null ? t0.hashCode() : 0);
        result = 31 * result + (t1 != null ? t1.hashCode() : 0);
        result = 31 * result + (t2 != null ? t2.hashCode() : 0);
        result = 31 * result + (productEntity != null ? productEntity.hashCode() : 0);
        return result;
    }
}
