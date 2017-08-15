package com.rengu.entity;

public class RG_LayoutDistanceEntity {

    private int id;

    private String site1;
    private String site2;
    private String distance;
    private String time;

    private String type;           //不同布局的类型，1:1个智能装配，1个人机协作；2：2个只能装配，1个人机协作；3：1个/2个智能装配，2个人机协作

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite1() {
        return site1;
    }

    public void setSite1(String site1) {
        this.site1 = site1;
    }

    public String getSite2() {
        return site2;
    }

    public void setSite2(String site2) {
        this.site2 = site2;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
