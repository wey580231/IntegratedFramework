package com.rengu.entity;

/**工艺表转换助手，结合工艺表将plan结果转换成3D车间需要的格式
 * Created by wey580231 on 2017/7/7.
 */
public class RG_ProcessAssisantEntity {

    private int id;
    private String processId;                       //工艺记录
    private String site;                            //地点
    private String task;                            //任务信息
    private String goods;                           //货物

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ProcessAssisantEntity that = (RG_ProcessAssisantEntity) o;

        if (id != that.id) return false;
        if (processId != null ? !processId.equals(that.processId) : that.processId != null) return false;
        if (site != null ? !site.equals(that.site) : that.site != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        return goods != null ? goods.equals(that.goods) : that.goods == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (processId != null ? processId.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (goods != null ? goods.hashCode() : 0);
        return result;
    }
}
