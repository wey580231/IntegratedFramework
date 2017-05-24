package com.rengu.entity;

import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
public class RG_ClubEntity {
    private String id;
    private String name;
    private Collection<RG_UserEntity> usersById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ClubEntity that = (RG_ClubEntity) o;

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

    public Collection<RG_UserEntity> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<RG_UserEntity> usersById) {
        this.usersById = usersById;
    }
}
