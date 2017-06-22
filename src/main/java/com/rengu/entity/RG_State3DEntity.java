package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_State3DEntity {
    private Integer id;
    private Integer layoutState;                //布局是否改变，0未改变，1为改变
    private String layoutId;                    //布局改变时，对应的layout索引值
    private Integer model;                      //仿真模式，0为无，1为仿真模式，2为实时模式
    private String snapshotId;                  //为仿真模式时，请求获取该snapshot对应的结果西信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLayoutState() {
        return layoutState;
    }

    public void setLayoutState(Integer layoutState) {
        this.layoutState = layoutState;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_State3DEntity that = (RG_State3DEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (layoutState != null ? !layoutState.equals(that.layoutState) : that.layoutState != null) return false;
        if (layoutId != null ? !layoutId.equals(that.layoutId) : that.layoutId != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return snapshotId != null ? snapshotId.equals(that.snapshotId) : that.snapshotId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (layoutState != null ? layoutState.hashCode() : 0);
        result = 31 * result + (layoutId != null ? layoutId.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (snapshotId != null ? snapshotId.hashCode() : 0);
        return result;
    }
}
