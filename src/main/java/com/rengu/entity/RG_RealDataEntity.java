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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_RealDataEntity that = (RG_RealDataEntity) o;

        if (id != that.id) return false;
        if (idResource != null ? !idResource.equals(that.idResource) : that.idResource != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (good != null ? !good.equals(that.good) : that.good != null) return false;
        if (startLocation != null ? !startLocation.equals(that.startLocation) : that.startLocation != null)
            return false;
        if (endLocation != null ? !endLocation.equals(that.endLocation) : that.endLocation != null) return false;
        if (valueType != null ? !valueType.equals(that.valueType) : that.valueType != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idResource != null ? idResource.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (good != null ? good.hashCode() : 0);
        result = 31 * result + (startLocation != null ? startLocation.hashCode() : 0);
        result = 31 * result + (endLocation != null ? endLocation.hashCode() : 0);
        result = 31 * result + (valueType != null ? valueType.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
