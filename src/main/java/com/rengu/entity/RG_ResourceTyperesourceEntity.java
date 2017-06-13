package com.rengu.entity;

/**
 * Created by wey580231 on 2017/5/24.
 */
public class RG_ResourceTyperesourceEntity {

    private String id;
    private int maxCapacityParallel;
    private int minCapacityParallel;
    private int capacitySequence;
    private float ratio;
    private RG_ResourceEntity resourceByResourceId;
    private RG_TyperescourceEntity typeresourceById;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxCapacityParallel() {
        return maxCapacityParallel;
    }

    public void setMaxCapacityParallel(int maxCapacityParallel) {
        this.maxCapacityParallel = maxCapacityParallel;
    }

    public int getMinCapacityParallel() {
        return minCapacityParallel;
    }

    public void setMinCapacityParallel(int minCapacityParallel) {
        this.minCapacityParallel = minCapacityParallel;
    }

    public int getCapacitySequence() {
        return capacitySequence;
    }

    public void setCapacitySequence(int capacitySequence) {
        this.capacitySequence = capacitySequence;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
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

        if (maxCapacityParallel != that.maxCapacityParallel) return false;
        if (minCapacityParallel != that.minCapacityParallel) return false;
        if (capacitySequence != that.capacitySequence) return false;
        if (Float.compare(that.ratio, ratio) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (resourceByResourceId != null ? !resourceByResourceId.equals(that.resourceByResourceId) : that.resourceByResourceId != null)
            return false;
        return typeresourceById != null ? typeresourceById.equals(that.typeresourceById) : that.typeresourceById == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + maxCapacityParallel;
        result = 31 * result + minCapacityParallel;
        result = 31 * result + capacitySequence;
        result = 31 * result + (ratio != +0.0f ? Float.floatToIntBits(ratio) : 0);
        result = 31 * result + (resourceByResourceId != null ? resourceByResourceId.hashCode() : 0);
        result = 31 * result + (typeresourceById != null ? typeresourceById.hashCode() : 0);
        return result;
    }
}
