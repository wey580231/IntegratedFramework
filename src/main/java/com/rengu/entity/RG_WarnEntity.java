package com.rengu.entity;

/**用户故障中心
 * Created by wey580231 on 2017/6/19.
 */
public class RG_WarnEntity {

    private String id;

    private String warnDesc;             //告警描述

    private Integer warnLevel;           //告警等级（严重、较高、一般）
    private Integer finished;            //处理完成

    private String warnType;             //告警类型(工序、订单、设备)
    private String warnId;               //对应warnType中的id

    private String warnDate;             //告警产生日期
    private String procDate;             //处理日期

    private RG_UserEntity user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarnDesc() {
        return warnDesc;
    }

    public void setWarnDesc(String warnDesc) {
        this.warnDesc = warnDesc;
    }

    public Integer getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnId() {
        return warnId;
    }

    public void setWarnId(String warnId) {
        this.warnId = warnId;
    }

    public String getWarnDate() {
        return warnDate;
    }

    public void setWarnDate(String warnDate) {
        this.warnDate = warnDate;
    }

    public String getProcDate() {
        return procDate;
    }

    public void setProcDate(String procDate) {
        this.procDate = procDate;
    }

    public RG_UserEntity getUser() {
        return user;
    }

    public void setUser(RG_UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_WarnEntity that = (RG_WarnEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (warnDesc != null ? !warnDesc.equals(that.warnDesc) : that.warnDesc != null) return false;
        if (warnLevel != null ? !warnLevel.equals(that.warnLevel) : that.warnLevel != null) return false;
        if (finished != null ? !finished.equals(that.finished) : that.finished != null) return false;
        if (warnType != null ? !warnType.equals(that.warnType) : that.warnType != null) return false;
        if (warnId != null ? !warnId.equals(that.warnId) : that.warnId != null) return false;
        if (warnDate != null ? !warnDate.equals(that.warnDate) : that.warnDate != null) return false;
        return procDate != null ? procDate.equals(that.procDate) : that.procDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (warnDesc != null ? warnDesc.hashCode() : 0);
        result = 31 * result + (warnLevel != null ? warnLevel.hashCode() : 0);
        result = 31 * result + (finished != null ? finished.hashCode() : 0);
        result = 31 * result + (warnType != null ? warnType.hashCode() : 0);
        result = 31 * result + (warnId != null ? warnId.hashCode() : 0);
        result = 31 * result + (warnDate != null ? warnDate.hashCode() : 0);
        result = 31 * result + (procDate != null ? procDate.hashCode() : 0);
        return result;
    }
}
