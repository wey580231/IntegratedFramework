package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_RealDataEntity {
    private int id;
    private String idResource;
    private String state;
    private String good;
    private String startLocation;
    private String endLocation;
    private String valueType;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdResource() {
        return idResource;
    }

    public void setIdResource(String idResource) {
        this.idResource = idResource;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((endLocation == null) ? 0 : endLocation.hashCode());
        result = prime * result + ((good == null) ? 0 : good.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((idResource == null) ? 0 : idResource.hashCode());
        result = prime * result
                + ((startLocation == null) ? 0 : startLocation.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result
                + ((valueType == null) ? 0 : valueType.hashCode());
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
        RG_RealDataEntity other = (RG_RealDataEntity) obj;
        if (endLocation == null) {
            if (other.endLocation != null)
                return false;
        } else if (!endLocation.equals(other.endLocation))
            return false;
        if (good == null) {
            if (other.good != null)
                return false;
        } else if (!good.equals(other.good))
            return false;
        if (id != other.id)
            return false;
        if (idResource == null) {
            if (other.idResource != null)
                return false;
        } else if (!idResource.equals(other.idResource))
            return false;
        if (startLocation == null) {
            if (other.startLocation != null)
                return false;
        } else if (!startLocation.equals(other.startLocation))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (valueType == null) {
            if (other.valueType != null)
                return false;
        } else if (!valueType.equals(other.valueType))
            return false;
        return true;
    }

}
