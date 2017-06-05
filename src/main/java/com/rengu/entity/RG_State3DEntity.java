package com.rengu.entity;

public class RG_State3DEntity {
    private int id;
    private int layoutState;
    private String layoutId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLayoutState() {
        return layoutState;
    }

    public void setLayoutState(int layoutState) {
        this.layoutState = layoutState;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result
                + ((layoutId == null) ? 0 : layoutId.hashCode());
        result = prime * result + layoutState;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RG_State3DEntity other = (RG_State3DEntity) obj;
        if (id != other.id)
            return false;
        if (layoutId == null) {
            if (other.layoutId != null)
                return false;
        } else if (!layoutId.equals(other.layoutId))
            return false;
        if (layoutState != other.layoutState)
            return false;
        return true;
    }

}
