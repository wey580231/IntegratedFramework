package com.rengu.entity;

import java.util.Date;

//布局调整

public class RG_AdjustLayoutEntity {

    private String id;                   //UUID
    private String name;                 //布局名称
    private String layoutDesc;           //布局描述
    private Date reportTime;             //上报时间
    private String origin;               //异常来源，3D车间、手工模拟
    private String type;                 //插入、更新
    private Integer state;               //异常状态，参照ErrorState类
    private Date processTime;            //处理时间

    private RG_LayoutEntity layout;      //对应的布局

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLayoutDesc() {
        return layoutDesc;
    }

    public void setLayoutDesc(String layoutDesc) {
        this.layoutDesc = layoutDesc;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public RG_LayoutEntity getLayout() {
        return layout;
    }

    public void setLayout(RG_LayoutEntity layout) {
        this.layout = layout;
    }
}
