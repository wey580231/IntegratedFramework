package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

/**
 * 快照管理树形节点
 * Created by wey580231 on 2017/6/16.
 */
public class RG_SnapshotNodeEntity {

    private String id;                      //UUID
    private String name;                    //节点名称

    private String level;                   //节点层级(top、middle、bottom)

//    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    private Set<RG_SnapshotNodeEntity> childs = new HashSet<RG_SnapshotNodeEntity>();      //子节点
    private RG_SnapshotNodeEntity parent;           //父节点
    private RG_SnapshotNodeEntity rootParent;       //根节点
    private RG_ScheduleEntity schedule;             //排程记录
    private Set<RG_PlanEntity> plans;               //计划表

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public RG_SnapshotNodeEntity getParent() {
        return parent;
    }

    public void setParent(RG_SnapshotNodeEntity parent) {
        this.parent = parent;
    }

    public Set<RG_SnapshotNodeEntity> getChilds() {
        return childs;
    }

    public void setChilds(Set<RG_SnapshotNodeEntity> childs) {
        this.childs = childs;
    }

    public RG_SnapshotNodeEntity getRootParent() {
        return rootParent;
    }

    public void setRootParent(RG_SnapshotNodeEntity rootParent) {
        this.rootParent = rootParent;
    }

    public RG_ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(RG_ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public Set<RG_PlanEntity> getPlans() {
        return plans;
    }

    public void setPlans(Set<RG_PlanEntity> plans) {
        this.plans = plans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_SnapshotNodeEntity that = (RG_SnapshotNodeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return level != null ? level.equals(that.level) : that.level == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }
}
