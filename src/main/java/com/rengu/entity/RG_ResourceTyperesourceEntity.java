package com.rengu.entity;

/**
 * Created by wey580231 on 2017/5/24.
 */
public class RG_ResourceTyperesourceEntity {

    private int id;
    private int maxCapacityParallel;
    private int minCapacityParallel;
    private int capacitySequence;
    private float ratio;
    private RG_ResourceEntity resourceByResourceId;
    private RG_TyperescourceEntity typeresourceById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinCapacityParallel() {
        return minCapacityParallel;
    }

    public void setMinCapacityParallel(int minCapacityParallel) {
        this.minCapacityParallel = minCapacityParallel;
    }

    public int getMaxCapacityParallel() {
        return maxCapacityParallel;
    }

    public void setMaxCapacityParallel(int maxCapacityParallel) {
        this.maxCapacityParallel = maxCapacityParallel;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + capacitySequence;
        result = prime * result + id;
        result = prime * result + maxCapacityParallel;
        result = prime * result + minCapacityParallel;
        result = prime * result + Float.floatToIntBits(ratio);
        result = prime
                * result
                + ((resourceByResourceId == null) ? 0 : resourceByResourceId
                .hashCode());
        result = prime
                * result
                + ((typeresourceById == null) ? 0 : typeresourceById.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RG_ResourceTyperesourceEntity other = (RG_ResourceTyperesourceEntity) obj;
        if (capacitySequence != other.capacitySequence)
            return false;
        if (id != other.id)
            return false;
        if (maxCapacityParallel != other.maxCapacityParallel)
            return false;
        if (minCapacityParallel != other.minCapacityParallel)
            return false;
        if (Float.floatToIntBits(ratio) != Float.floatToIntBits(other.ratio))
            return false;
        if (resourceByResourceId == null) {
            if (other.resourceByResourceId != null)
                return false;
        } else if (!resourceByResourceId.equals(other.resourceByResourceId))
            return false;
        if (typeresourceById == null) {
            if (other.typeresourceById != null)
                return false;
        } else if (!typeresourceById.equals(other.typeresourceById))
            return false;
        return true;
    }

}
