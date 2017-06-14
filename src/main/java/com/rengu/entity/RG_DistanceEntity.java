package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by wey580231 on 2017/5/24.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_DistanceEntity {
    private String id;
    private RG_SiteEntity startSite;
    private RG_SiteEntity endSite;
    private int distance;
    private int time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RG_SiteEntity getStartSite() {
        return startSite;
    }

    public void setStartSite(RG_SiteEntity startSite) {
        this.startSite = startSite;
    }

    public RG_SiteEntity getEndSite() {
        return endSite;
    }

    public void setEndSite(RG_SiteEntity endSite) {
        this.endSite = endSite;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_DistanceEntity that = (RG_DistanceEntity) o;

        if (distance != that.distance) return false;
        if (time != that.time) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startSite != null ? !startSite.equals(that.startSite) : that.startSite != null) return false;
        return endSite != null ? endSite.equals(that.endSite) : that.endSite == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startSite != null ? startSite.hashCode() : 0);
        result = 31 * result + (endSite != null ? endSite.hashCode() : 0);
        result = 31 * result + distance;
        result = 31 * result + time;
        return result;
    }
}
