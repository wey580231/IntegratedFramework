package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_LayoutDetailEntity {
    private String id;
    private String item;
    private String pos;
    private String state;
    private String exist;
    private RG_LayoutEntity layout;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public RG_LayoutEntity getLayout() {
        return layout;
    }

    public void setLayout(RG_LayoutEntity layout) {
        this.layout = layout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_LayoutDetailEntity that = (RG_LayoutDetailEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (pos != null ? !pos.equals(that.pos) : that.pos != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (exist != null ? !exist.equals(that.exist) : that.exist != null) return false;
        return layout != null ? layout.equals(that.layout) : that.layout == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (exist != null ? exist.hashCode() : 0);
        result = 31 * result + (layout != null ? layout.hashCode() : 0);
        return result;
    }

    public String toJson() {
        return "{\"id\":" + id + "," +
                "\"item\":" + "\"" + item + "\"" + "," +
                "\"pos\":" + "\"" + pos + "\"" + "," +
                "\"state\":" + "\"" + state + "\"" + "," +
                "\"exist\":" + "\"" + exist + "\"" +
                "}";
    }
}
