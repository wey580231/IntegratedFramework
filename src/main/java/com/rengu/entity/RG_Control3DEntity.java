package com.rengu.entity;

public class RG_Control3DEntity {
    private int id;
    private int model;
    private int controlState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getControlState() {
        return controlState;
    }

    public void setControlState(int controlState) {
        this.controlState = controlState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + controlState;
        result = prime * result + id;
        result = prime * result + model;
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
        RG_Control3DEntity other = (RG_Control3DEntity) obj;
        if (controlState != other.controlState)
            return false;
        if (id != other.id)
            return false;
        if (model != other.model)
            return false;
        return true;
    }

}
