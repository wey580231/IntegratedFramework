package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wey580231 on 2017/5/23.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_GroupresourceEntity {
    private String id;
    private String name;
    private Byte external;
    private String idSite0;
    private Byte state;
    private String color;
    private String idSite;
    private RG_ProviderEntity providerByIdProvider;
    private Set<RG_SiteEntity> sitesById = new HashSet<RG_SiteEntity>();
    private Set<RG_ResourceEntity> resourcesById = new HashSet<RG_ResourceEntity>();
    private Set<RG_ScheduleEntity> schedules = new HashSet<RG_ScheduleEntity>();

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

    public Byte getExternal() {
        return external;
    }

    public void setExternal(Byte external) {
        this.external = external;
    }

    public String getIdSite0() {
        return idSite0;
    }

    public void setIdSite0(String idSite0) {
        this.idSite0 = idSite0;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_GroupresourceEntity that = (RG_GroupresourceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (external != null ? !external.equals(that.external) : that.external != null) return false;
        if (idSite0 != null ? !idSite0.equals(that.idSite0) : that.idSite0 != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (idSite != null ? !idSite.equals(that.idSite) : that.idSite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (external != null ? external.hashCode() : 0);
        result = 31 * result + (idSite0 != null ? idSite0.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (idSite != null ? idSite.hashCode() : 0);
        return result;
    }

    public RG_ProviderEntity getProviderByIdProvider() {
        return providerByIdProvider;
    }

    public void setProviderByIdProvider(RG_ProviderEntity providerByIdProvider) {
        this.providerByIdProvider = providerByIdProvider;
    }

    public Set<RG_ResourceEntity> getResourcesById() {
        return resourcesById;
    }

    public void setResourcesById(Set<RG_ResourceEntity> resourcesById) {
        this.resourcesById = resourcesById;
    }

    public Set<RG_SiteEntity> getSitesById() {
        return sitesById;
    }

    public void setSitesById(Set<RG_SiteEntity> sitesById) {
        this.sitesById = sitesById;
    }

    public Set<RG_ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<RG_ScheduleEntity> schedules) {
        this.schedules = schedules;
    }

}
