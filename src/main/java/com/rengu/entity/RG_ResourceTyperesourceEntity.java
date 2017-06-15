package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by wey580231 on 2017/5/24.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_ResourceTyperesourceEntity {

    private String id;
    private Integer maxCapacityParallel;
    private Integer minCapacityParallel;
    private Integer capacitySequence;
    private Float ratio;
    private RG_ResourceEntity resourceByResourceId;
    private RG_TyperescourceEntity typeresourceById;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMaxCapacityParallel() {
        return maxCapacityParallel;
    }

    public void setMaxCapacityParallel(Integer maxCapacityParallel) {
        this.maxCapacityParallel = maxCapacityParallel;
    }

    public Integer getMinCapacityParallel() {
        return minCapacityParallel;
    }

    public void setMinCapacityParallel(Integer minCapacityParallel) {
        this.minCapacityParallel = minCapacityParallel;
    }

    public Integer getCapacitySequence() {
        return capacitySequence;
    }

    public void setCapacitySequence(Integer capacitySequence) {
        this.capacitySequence = capacitySequence;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    public RG_ResourceEntity getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(RG_ResourceEntity resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }

    public RG_TyperescourceEntity getTyperesourceById() {
        return typeresourceById;
    }

    public void setTyperesourceById(RG_TyperescourceEntity typeresourceById) {
        this.typeresourceById = typeresourceById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ResourceTyperesourceEntity that = (RG_ResourceTyperesourceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (maxCapacityParallel != null ? !maxCapacityParallel.equals(that.maxCapacityParallel) : that.maxCapacityParallel != null)
            return false;
        if (minCapacityParallel != null ? !minCapacityParallel.equals(that.minCapacityParallel) : that.minCapacityParallel != null)
            return false;
        if (capacitySequence != null ? !capacitySequence.equals(that.capacitySequence) : that.capacitySequence != null)
            return false;
        if (ratio != null ? !ratio.equals(that.ratio) : that.ratio != null) return false;
        if (resourceByResourceId != null ? !resourceByResourceId.equals(that.resourceByResourceId) : that.resourceByResourceId != null)
            return false;
        return typeresourceById != null ? typeresourceById.equals(that.typeresourceById) : that.typeresourceById == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (maxCapacityParallel != null ? maxCapacityParallel.hashCode() : 0);
        result = 31 * result + (minCapacityParallel != null ? minCapacityParallel.hashCode() : 0);
        result = 31 * result + (capacitySequence != null ? capacitySequence.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        result = 31 * result + (resourceByResourceId != null ? resourceByResourceId.hashCode() : 0);
        result = 31 * result + (typeresourceById != null ? typeresourceById.hashCode() : 0);
        return result;
    }
}
