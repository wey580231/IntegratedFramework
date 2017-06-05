package com.rengu.entity;

public class RG_EmulateDataEntity {

    private int id;
    private String idResource;
    private String state;
    private String good;
    private String startLocation;
    private String endLocation;
    private String startTime;
    private String endTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((endLocation == null) ? 0 : endLocation.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((good == null) ? 0 : good.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((idResource == null) ? 0 : idResource.hashCode());
        result = prime * result
                + ((startLocation == null) ? 0 : startLocation.hashCode());
        result = prime * result
                + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        RG_EmulateDataEntity other = (RG_EmulateDataEntity) obj;
        if (endLocation == null) {
            if (other.endLocation != null)
                return false;
        } else if (!endLocation.equals(other.endLocation))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
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
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }

}
