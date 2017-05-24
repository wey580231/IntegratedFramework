package com.rengu.entity;

/**
 * Created by wey580231 on 2017/5/23.
 */
public class RG_ConfigEntity {
    private String id;
    private String name;
    private String value;
    private String description;
    private String editable;
    private RG_UserEntity userByIdUser;
    private RG_ClubEntity clubByIdClub;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ConfigEntity that = (RG_ConfigEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (editable != null ? !editable.equals(that.editable) : that.editable != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (editable != null ? editable.hashCode() : 0);
        return result;
    }

    public RG_UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(RG_UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    public RG_ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(RG_ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }
}
