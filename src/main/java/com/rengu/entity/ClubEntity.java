package com.rengu.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "club", schema = "testdatabase", catalog = "")
public class ClubEntity {
    private String id;
    private String name;
    private Collection<ConfigEntity> configsById;
    private Collection<GroupresourceEntity> groupresourcesById;
    private Collection<OrderEntity> ordersById;
    private Collection<PlanEntity> plansById;
    private Collection<ProviderEntity> providersById;
    private Collection<ResourceEntity> resourcesById;
    private Collection<UserEntity> usersById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClubEntity that = (ClubEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clubByIdClub")
    public Collection<ConfigEntity> getConfigsById() {
        return configsById;
    }

    public void setConfigsById(Collection<ConfigEntity> configsById) {
        this.configsById = configsById;
    }

    @OneToMany(mappedBy = "clubByIdClub")
    public Collection<GroupresourceEntity> getGroupresourcesById() {
        return groupresourcesById;
    }

    public void setGroupresourcesById(Collection<GroupresourceEntity> groupresourcesById) {
        this.groupresourcesById = groupresourcesById;
    }

    @OneToMany(mappedBy = "clubByIdClub")
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "clubByIdClub")
    public Collection<PlanEntity> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<PlanEntity> plansById) {
        this.plansById = plansById;
    }

    @OneToMany(mappedBy = "clubByIdClub")
    public Collection<ProviderEntity> getProvidersById() {
        return providersById;
    }

    public void setProvidersById(Collection<ProviderEntity> providersById) {
        this.providersById = providersById;
    }

    @OneToMany(mappedBy = "clubByIdClub")
    public Collection<ResourceEntity> getResourcesById() {
        return resourcesById;
    }

    public void setResourcesById(Collection<ResourceEntity> resourcesById) {
        this.resourcesById = resourcesById;
    }

    @OneToMany(mappedBy = "clubByIdClub")
    public Collection<UserEntity> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UserEntity> usersById) {
        this.usersById = usersById;
    }
}
