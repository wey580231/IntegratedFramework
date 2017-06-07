package com.rengu.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rengu.util.Tools;

import java.util.Iterator;
import java.util.Set;

public class RG_LayoutEntity {

    private int id;
    private String name;
    private Set<RG_LayoutDetailEntity> details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RG_LayoutDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(Set<RG_LayoutDetailEntity> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RG_LayoutEntity other = (RG_LayoutEntity) obj;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public String toJson() {
        String detailString = "{ \"result\":\"0\""+ "," +
                                "\"id\":" + "\"" + name + "\",";
        detailString += ("\"data\":" + "[");
        Iterator<RG_LayoutDetailEntity> iter = details.iterator();

        int index = 0;

        while (iter.hasNext()) {
            detailString += iter.next().toJson();
            index++;

            if (index < details.size()) {
                detailString += ",";
            }
        }

        detailString += ("]}");

        return detailString;
    }
}
