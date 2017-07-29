package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_LayoutEntity {

    private String id;
    private String name;

    private String layoutDesc;       //布局描述
    private String imgPath;         //文件路径

    @JsonIgnore
    private Set<RG_LayoutDetailEntity> details = new HashSet<RG_LayoutDetailEntity>();
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ScheduleId")
    private Set<RG_ScheduleEntity> schedules = new HashSet<RG_ScheduleEntity>();

    private Set<RG_AdjustLayoutEntity> errorLayouts = new HashSet<RG_AdjustLayoutEntity>();

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

    public String getLayoutDesc() {
        return layoutDesc;
    }

    public void setLayoutDesc(String layoutDesc) {
        this.layoutDesc = layoutDesc;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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

    public Set<RG_AdjustLayoutEntity> getErrorLayouts() {
        return errorLayouts;
    }

    public void setErrorLayouts(Set<RG_AdjustLayoutEntity> errorLayouts) {
        this.errorLayouts = errorLayouts;
    }
}
