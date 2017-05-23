package com.rengu.entity;

import javax.persistence.*;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "shift", schema = "testdatabase", catalog = "")
public class ShiftEntity {
    private String id;
    private String name;
    private Byte type;
    private String slot;
    private String id0;
    private Short extra;

    @Id
    @Column(name = "id", nullable = false, length = 20)
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
    @Column(name = "type", nullable = true)
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Slot", nullable = true, length = 255)
    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    @Basic
    @Column(name = "id0", nullable = true, length = 20)
    public String getId0() {
        return id0;
    }

    public void setId0(String id0) {
        this.id0 = id0;
    }

    @Basic
    @Column(name = "extra", nullable = true)
    public Short getExtra() {
        return extra;
    }

    public void setExtra(Short extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShiftEntity that = (ShiftEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (slot != null ? !slot.equals(that.slot) : that.slot != null) return false;
        if (id0 != null ? !id0.equals(that.id0) : that.id0 != null) return false;
        if (extra != null ? !extra.equals(that.extra) : that.extra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (slot != null ? slot.hashCode() : 0);
        result = 31 * result + (id0 != null ? id0.hashCode() : 0);
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        return result;
    }
}
