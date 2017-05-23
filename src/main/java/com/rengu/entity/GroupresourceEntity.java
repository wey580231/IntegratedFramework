package com.rengu.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "groupresource", schema = "testdatabase", catalog = "")
public class GroupresourceEntity {
    private String id;
    private String name;
    private String idProvider;
    private String idClub;
    private Byte external;
    private String idSite0;
    private Byte state;
    private String color;
    private String idSite;
    private ProviderEntity providerByIdProvider;
    private ClubEntity clubByIdClub;
    private Collection<PlanEntity> plansById;
    private Collection<ResourceEntity> resourcesById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "idProvider")
    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    @Basic
    @Column(name = "idClub")
    public String getIdClub() {
        return idClub;
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    @Basic
    @Column(name = "external")
    public Byte getExternal() {
        return external;
    }

    public void setExternal(Byte external) {
        this.external = external;
    }

    @Basic
    @Column(name = "idSite0")
    public String getIdSite0() {
        return idSite0;
    }

    public void setIdSite0(String idSite0) {
        this.idSite0 = idSite0;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "IdSite")
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

        GroupresourceEntity that = (GroupresourceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idProvider != null ? !idProvider.equals(that.idProvider) : that.idProvider != null) return false;
        if (idClub != null ? !idClub.equals(that.idClub) : that.idClub != null) return false;
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
        result = 31 * result + (idProvider != null ? idProvider.hashCode() : 0);
        result = 31 * result + (idClub != null ? idClub.hashCode() : 0);
        result = 31 * result + (external != null ? external.hashCode() : 0);
        result = 31 * result + (idSite0 != null ? idSite0.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (idSite != null ? idSite.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idProvider", referencedColumnName = "id")
    public ProviderEntity getProviderByIdProvider() {
        return providerByIdProvider;
    }

    public void setProviderByIdProvider(ProviderEntity providerByIdProvider) {
        this.providerByIdProvider = providerByIdProvider;
    }

    @ManyToOne
    @JoinColumn(name = "idClub", referencedColumnName = "id")
    public ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }

    @OneToMany(mappedBy = "groupresourceByIdGroupResource")
    public Collection<PlanEntity> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<PlanEntity> plansById) {
        this.plansById = plansById;
    }

    @OneToMany(mappedBy = "groupresourceByIdGroupResource")
    public Collection<ResourceEntity> getResourcesById() {
        return resourcesById;
    }

    public void setResourcesById(Collection<ResourceEntity> resourcesById) {
        this.resourcesById = resourcesById;
    }
}
