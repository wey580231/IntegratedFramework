package com.rengu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by XY on 2017/11/8.
 */
public class ApsResource2EntityPK implements Serializable {
    private String id;
    private String idClub;

    @Column(name = "id")
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "idClub")
    @Id
    public String getIdClub() {
        return idClub;
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApsResource2EntityPK that = (ApsResource2EntityPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idClub != null ? !idClub.equals(that.idClub) : that.idClub != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idClub != null ? idClub.hashCode() : 0);
        return result;
    }
}
