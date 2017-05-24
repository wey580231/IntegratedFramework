package com.rengu.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "user", schema = "testdatabase", catalog = "")
public class UserEntity {
    private String id;
    private String name;
    private String idClub;
    private String idClient;
    private String idProvider;
    private Byte authority;
    private String password;
    private Collection<ConfigEntity> configsById;
    private Collection<ResourceEntity> resourcesById;
    private ClubEntity clubByIdClub;

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
    @Column(name = "idClub")
    public String getIdClub() {
        return idClub;
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    @Basic
    @Column(name = "idClient")
    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
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
    @Column(name = "authority")
    public Byte getAuthority() {
        return authority;
    }

    public void setAuthority(Byte authority) {
        this.authority = authority;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idClub != null ? !idClub.equals(that.idClub) : that.idClub != null) return false;
        if (idClient != null ? !idClient.equals(that.idClient) : that.idClient != null) return false;
        if (idProvider != null ? !idProvider.equals(that.idProvider) : that.idProvider != null) return false;
        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idClub != null ? idClub.hashCode() : 0);
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (idProvider != null ? idProvider.hashCode() : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<ConfigEntity> getConfigsById() {
        return configsById;
    }

    public void setConfigsById(Collection<ConfigEntity> configsById) {
        this.configsById = configsById;
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<ResourceEntity> getResourcesById() {
        return resourcesById;
    }

    public void setResourcesById(Collection<ResourceEntity> resourcesById) {
        this.resourcesById = resourcesById;
    }

    @ManyToOne
    @JoinColumn(name = "idClub", referencedColumnName = "id")
    public ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }
}
