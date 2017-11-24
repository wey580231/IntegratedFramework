package com.rengu.entity;

import javax.persistence.*;

/**
 * Created by XY on 2017/11/20.
 */
@Entity
@Table(name = "aps_resource_typeresource1", schema = "testdatabase", catalog = "")
public class ApsResourceTyperesource1Entity {
    private String idResource;
    private String idTypeResource;
    private String uniGroupResource;
    private Integer minCapacityParallel;
    private Integer maxCapacityParallel;
    private Integer capacitySequence;
    private Integer delaySequence;
    private Double ratio;
    private String idIcon;
    private int id;

    @Basic
    @Column(name = "idResource")
    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String idResource) {
        this.idResource = idResource;
    }

    @Basic
    @Column(name = "idTypeResource")
    public String getIdTypeResource() {
        return idTypeResource;
    }

    public void setIdTypeResource(String idTypeResource) {
        this.idTypeResource = idTypeResource;
    }

    @Basic
    @Column(name = "uniGroupResource")
    public String getUniGroupResource() {
        return uniGroupResource;
    }

    public void setUniGroupResource(String uniGroupResource) {
        this.uniGroupResource = uniGroupResource;
    }

    @Basic
    @Column(name = "minCapacityParallel")
    public Integer getMinCapacityParallel() {
        return minCapacityParallel;
    }

    public void setMinCapacityParallel(Integer minCapacityParallel) {
        this.minCapacityParallel = minCapacityParallel;
    }

    @Basic
    @Column(name = "maxCapacityParallel")
    public Integer getMaxCapacityParallel() {
        return maxCapacityParallel;
    }

    public void setMaxCapacityParallel(Integer maxCapacityParallel) {
        this.maxCapacityParallel = maxCapacityParallel;
    }

    @Basic
    @Column(name = "capacitySequence")
    public Integer getCapacitySequence() {
        return capacitySequence;
    }

    public void setCapacitySequence(Integer capacitySequence) {
        this.capacitySequence = capacitySequence;
    }

    @Basic
    @Column(name = "delaySequence")
    public Integer getDelaySequence() {
        return delaySequence;
    }

    public void setDelaySequence(Integer delaySequence) {
        this.delaySequence = delaySequence;
    }

    @Basic
    @Column(name = "ratio")
    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Basic
    @Column(name = "idIcon")
    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApsResourceTyperesource1Entity that = (ApsResourceTyperesource1Entity) o;

        if (id != that.id) return false;
        if (idResource != null ? !idResource.equals(that.idResource) : that.idResource != null) return false;
        if (idTypeResource != null ? !idTypeResource.equals(that.idTypeResource) : that.idTypeResource != null)
            return false;
        if (uniGroupResource != null ? !uniGroupResource.equals(that.uniGroupResource) : that.uniGroupResource != null)
            return false;
        if (minCapacityParallel != null ? !minCapacityParallel.equals(that.minCapacityParallel) : that.minCapacityParallel != null)
            return false;
        if (maxCapacityParallel != null ? !maxCapacityParallel.equals(that.maxCapacityParallel) : that.maxCapacityParallel != null)
            return false;
        if (capacitySequence != null ? !capacitySequence.equals(that.capacitySequence) : that.capacitySequence != null)
            return false;
        if (delaySequence != null ? !delaySequence.equals(that.delaySequence) : that.delaySequence != null)
            return false;
        if (ratio != null ? !ratio.equals(that.ratio) : that.ratio != null) return false;
        if (idIcon != null ? !idIcon.equals(that.idIcon) : that.idIcon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idResource != null ? idResource.hashCode() : 0;
        result = 31 * result + (idTypeResource != null ? idTypeResource.hashCode() : 0);
        result = 31 * result + (uniGroupResource != null ? uniGroupResource.hashCode() : 0);
        result = 31 * result + (minCapacityParallel != null ? minCapacityParallel.hashCode() : 0);
        result = 31 * result + (maxCapacityParallel != null ? maxCapacityParallel.hashCode() : 0);
        result = 31 * result + (capacitySequence != null ? capacitySequence.hashCode() : 0);
        result = 31 * result + (delaySequence != null ? delaySequence.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        result = 31 * result + (idIcon != null ? idIcon.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
