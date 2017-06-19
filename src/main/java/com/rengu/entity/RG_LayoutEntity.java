package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Iterator;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_LayoutEntity {

    private String id;
    private String name;
    private Set<RG_LayoutDetailEntity> details;
    private Set<RG_ScheduleEntity> schedules;

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

    public Set<RG_LayoutDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(Set<RG_LayoutDetailEntity> details) {
        this.details = details;
    }


    public Set<RG_ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<RG_ScheduleEntity> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_LayoutEntity that = (RG_LayoutEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return details != null ? details.equals(that.details) : that.details == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }

    public String toJson() {
        String detailString = "{ \"result\":\"0\"" + "," +
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
