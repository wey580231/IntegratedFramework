package com.rengu.entity;

public class RG_SiteTypeEntity {

    private int id;
    private String resource;
    private String name;
    private String type;
    private int x;
    private int y;
    private String color;
    private String idIcon;
    private int sizeIcon;
    private int capacity;

    private String layoutType;           //不同布局的类型，1:1个智能装配，1个人机协作；2：2个只能装配，1个人机协作；3：1个/2个智能装配，2个人机协作

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    public int getSizeIcon() {
        return sizeIcon;
    }

    public void setSizeIcon(int sizeIcon) {
        this.sizeIcon = sizeIcon;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }
}
