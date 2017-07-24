package com.rengu.entity;

/**
 * 工艺表转换助手，结合工艺表将plan结果转换成3D车间需要的格式
 * Created by wey580231 on 2017/7/7.
 */
public class RG_ProcessAssisantEntity {

    private int id;
    private String processId;                       //工艺记录
    private String site;                            //地点
    private String task;                            //任务信息
    private String goods;                           //货物

    private String autoCreateProcess;               //是否需要紧接着当前工序自动产生吓一条工序，目前只对getRaw和get_product有效
    private String nextTask;                        //工序转换的类型，为getRaw则自动转换movein，为get_product自动转换put_product
    private Integer distance;                       //运动的距离

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getAutoCreateProcess() {
        return autoCreateProcess;
    }

    public void setAutoCreateProcess(String autoCreateProcess) {
        this.autoCreateProcess = autoCreateProcess;
    }

    public String getNextTask() {
        return nextTask;
    }

    public void setNextTask(String nextTask) {
        this.nextTask = nextTask;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
