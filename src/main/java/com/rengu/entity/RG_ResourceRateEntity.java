package com.rengu.entity;

/**
 * Created by XY on 2017/10/17.
 */
public class RG_ResourceRateEntity {
    private String id;
    private RG_SnapshotNodeEntity idSnapshot;
    private RG_ResourceEntity idResource;
    private double rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RG_SnapshotNodeEntity getIdSnapshot() {
        return idSnapshot;
    }

    public void setIdSnapshot(RG_SnapshotNodeEntity idSnapshot) {
        this.idSnapshot = idSnapshot;
    }

    public RG_ResourceEntity getIdResource() {
        return idResource;
    }

    public void setIdResource(RG_ResourceEntity idResource) {
        this.idResource = idResource;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
