package com.rengu.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "product", schema = "testdatabase", catalog = "")
public class ProductEntity {
    private String id;
    private String name;
    private String type;
    private String ref;
    private String depth;
    private Short stock;
    private String unit;
    private String model;
    private String state;
    private String supplyMethod;
    private Collection<OrderEntity> ordersById;
    private Collection<ProcessEntity> processesById;

    @Id
    @Column(name = "id", nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "ref", nullable = true, length = 255)
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Basic
    @Column(name = "depth", nullable = true, length = 255)
    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Basic
    @Column(name = "stock", nullable = true)
    public Short getStock() {
        return stock;
    }

    public void setStock(Short stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 20)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "model", nullable = true, length = 100)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 20)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "supplyMethod", nullable = true, length = 20)
    public String getSupplyMethod() {
        return supplyMethod;
    }

    public void setSupplyMethod(String supplyMethod) {
        this.supplyMethod = supplyMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (ref != null ? !ref.equals(that.ref) : that.ref != null) return false;
        if (depth != null ? !depth.equals(that.depth) : that.depth != null) return false;
        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (supplyMethod != null ? !supplyMethod.equals(that.supplyMethod) : that.supplyMethod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ref != null ? ref.hashCode() : 0);
        result = 31 * result + (depth != null ? depth.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (supplyMethod != null ? supplyMethod.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productByIdProduct")
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "productByIdProduct")
    public Collection<ProcessEntity> getProcessesById() {
        return processesById;
    }

    public void setProcessesById(Collection<ProcessEntity> processesById) {
        this.processesById = processesById;
    }
}
