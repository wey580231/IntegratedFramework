package com.rengu.entity;

import java.util.Date;

/**设备状态信息
 * Created by wey580231 on 2017/6/28.
 */
public class RG_ResourceStateEntity {

    private int id;
    private String idResource;                  //设备编号
    private String resourceName;                //设备名称
    private String manufacturer;                //设备厂商
    private String idTask;                      //当前任务号
    private short ordToParentTask;              //工序号
    private String idClub;                      //客户名称
    private String idProduct;                   //零件编号
    private String productName;                 //零件名称
    private Date t1Task;                        //工序开始时间
    private Date t2Task;                        //工序占用时间
    private Date currTime;                      //当前时间
    private Date t1RealTask;                    //工序实际开始时间
    private Date t2RealTask;                    //工序实际完工时间
    private short state;                        //状态
    private float bootstrapTime;                //开机时间比
    private float awaitTime;                    //待机时间比
    private float processTime;                  //加工时间比

    private RG_ResourceEntity resourceEntity;   //资源

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

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public short getOrdToParentTask() {
        return ordToParentTask;
    }

    public void setOrdToParentTask(short ordToParentTask) {
        this.ordToParentTask = ordToParentTask;
    }

    public String getIdClub() {
        return idClub;
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getT1Task() {
        return t1Task;
    }

    public void setT1Task(Date t1Task) {
        this.t1Task = t1Task;
    }

    public Date getT2Task() {
        return t2Task;
    }

    public void setT2Task(Date t2Task) {
        this.t2Task = t2Task;
    }

    public Date getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Date currTime) {
        this.currTime = currTime;
    }

    public Date getT1RealTask() {
        return t1RealTask;
    }

    public void setT1RealTask(Date t1RealTask) {
        this.t1RealTask = t1RealTask;
    }

    public Date getT2RealTask() {
        return t2RealTask;
    }

    public void setT2RealTask(Date t2RealTask) {
        this.t2RealTask = t2RealTask;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public float getBootstrapTime() {
        return bootstrapTime;
    }

    public void setBootstrapTime(float bootstrapTime) {
        this.bootstrapTime = bootstrapTime;
    }

    public float getAwaitTime() {
        return awaitTime;
    }

    public void setAwaitTime(float awaitTime) {
        this.awaitTime = awaitTime;
    }

    public float getProcessTime() {
        return processTime;
    }

    public void setProcessTime(float processTime) {
        this.processTime = processTime;
    }

    public RG_ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(RG_ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ResourceStateEntity that = (RG_ResourceStateEntity) o;

        if (id != that.id) return false;
        if (ordToParentTask != that.ordToParentTask) return false;
        if (state != that.state) return false;
        if (Float.compare(that.bootstrapTime, bootstrapTime) != 0) return false;
        if (Float.compare(that.awaitTime, awaitTime) != 0) return false;
        if (Float.compare(that.processTime, processTime) != 0) return false;
        if (idResource != null ? !idResource.equals(that.idResource) : that.idResource != null) return false;
        if (resourceName != null ? !resourceName.equals(that.resourceName) : that.resourceName != null) return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (idTask != null ? !idTask.equals(that.idTask) : that.idTask != null) return false;
        if (idClub != null ? !idClub.equals(that.idClub) : that.idClub != null) return false;
        if (idProduct != null ? !idProduct.equals(that.idProduct) : that.idProduct != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (t1Task != null ? !t1Task.equals(that.t1Task) : that.t1Task != null) return false;
        if (t2Task != null ? !t2Task.equals(that.t2Task) : that.t2Task != null) return false;
        if (currTime != null ? !currTime.equals(that.currTime) : that.currTime != null) return false;
        if (t1RealTask != null ? !t1RealTask.equals(that.t1RealTask) : that.t1RealTask != null) return false;
        return t2RealTask != null ? t2RealTask.equals(that.t2RealTask) : that.t2RealTask == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idResource != null ? idResource.hashCode() : 0);
        result = 31 * result + (resourceName != null ? resourceName.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (idTask != null ? idTask.hashCode() : 0);
        result = 31 * result + (int) ordToParentTask;
        result = 31 * result + (idClub != null ? idClub.hashCode() : 0);
        result = 31 * result + (idProduct != null ? idProduct.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (t1Task != null ? t1Task.hashCode() : 0);
        result = 31 * result + (t2Task != null ? t2Task.hashCode() : 0);
        result = 31 * result + (currTime != null ? currTime.hashCode() : 0);
        result = 31 * result + (t1RealTask != null ? t1RealTask.hashCode() : 0);
        result = 31 * result + (t2RealTask != null ? t2RealTask.hashCode() : 0);
        result = 31 * result + (int) state;
        result = 31 * result + (bootstrapTime != +0.0f ? Float.floatToIntBits(bootstrapTime) : 0);
        result = 31 * result + (awaitTime != +0.0f ? Float.floatToIntBits(awaitTime) : 0);
        result = 31 * result + (processTime != +0.0f ? Float.floatToIntBits(processTime) : 0);
        return result;
    }
}
