package com.rengu.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "process", schema = "testdatabase", catalog = "")
public class ProcessEntity {
    private String id;
    private String name;
    private String idRoot;
    private String idParent;
    private String idProduct;
    private Short ordToRoot;
    private String ordToRootChild;
    private Short ordToParent;
    private Byte typeShift;
    private String preemptive;
    private String exclusiveJob;
    private String exclusiveOrder;
    private String coupledTypeOrder;
    private String idCoupled;
    private String idCoupledT1;
    private String idCoupledT2;
    private String idCoupledTypeShift;
    private String idCoupledShift;
    private String idCoupledGroupResource;
    private String idCoupledTypeResource;
    private String idCoupledResouce;
    private String idCoupledTypeSite;
    private String idCoupledSite;
    private String slot1;
    private String slot2;
    private Short initTime;
    private Short unitTime;
    private Short postTime;
    private Short checkTime;
    private Short delta;
    private Short estimate;
    private String continuous;
    private String idExclusive;
    private String idPrec;
    private String idSucc;
    private Short minTimeSucc;
    private Short maxTimeSucc;
    private Short quantity;
    private Short unitQuantity;
    private Short minQtySwitch;
    private Short maxQtySwitch;
    private Short modQtySwitch;
    private String idSwitch;
    private Short maxResourceDivision;
    private Short minResourceDivision;
    private Short modResourceDivision;
    private Short minTimeDivision;
    private Short maxTimeDivision;
    private Short modTimeDivision;
    private Short minQtyDivision;
    private Short maxQtyDivision;
    private Short modQtyDivision;
    private Short minQtyBatch;
    private Short maxQtyBatch;
    private Short modQtyBatch;
    private Short minTimeBatch;
    private Short maxTimeBatch;
    private Short modTimeBatch;
    private String batch;
    private String idIcon;
    private Short nbTask;
    private Short rgSwitch;
    private Collection<AssisantprocessEntity> assisantprocessesById;
    private Collection<PlanEntity> plansById;
    private ProductEntity productByIdProduct;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "idRoot")
    public String getIdRoot() {
        return idRoot;
    }

    public void setIdRoot(String idRoot) {
        this.idRoot = idRoot;
    }

    @Basic
    @Column(name = "idParent")
    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    @Basic
    @Column(name = "idProduct")
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "ordToRoot")
    public Short getOrdToRoot() {
        return ordToRoot;
    }

    public void setOrdToRoot(Short ordToRoot) {
        this.ordToRoot = ordToRoot;
    }

    @Basic
    @Column(name = "OrdToRootChild")
    public String getOrdToRootChild() {
        return ordToRootChild;
    }

    public void setOrdToRootChild(String ordToRootChild) {
        this.ordToRootChild = ordToRootChild;
    }

    @Basic
    @Column(name = "ordToParent")
    public Short getOrdToParent() {
        return ordToParent;
    }

    public void setOrdToParent(Short ordToParent) {
        this.ordToParent = ordToParent;
    }

    @Basic
    @Column(name = "typeShift")
    public Byte getTypeShift() {
        return typeShift;
    }

    public void setTypeShift(Byte typeShift) {
        this.typeShift = typeShift;
    }

    @Basic
    @Column(name = "preemptive")
    public String getPreemptive() {
        return preemptive;
    }

    public void setPreemptive(String preemptive) {
        this.preemptive = preemptive;
    }

    @Basic
    @Column(name = "exclusiveJob")
    public String getExclusiveJob() {
        return exclusiveJob;
    }

    public void setExclusiveJob(String exclusiveJob) {
        this.exclusiveJob = exclusiveJob;
    }

    @Basic
    @Column(name = "exclusiveOrder")
    public String getExclusiveOrder() {
        return exclusiveOrder;
    }

    public void setExclusiveOrder(String exclusiveOrder) {
        this.exclusiveOrder = exclusiveOrder;
    }

    @Basic
    @Column(name = "coupledTypeOrder")
    public String getCoupledTypeOrder() {
        return coupledTypeOrder;
    }

    public void setCoupledTypeOrder(String coupledTypeOrder) {
        this.coupledTypeOrder = coupledTypeOrder;
    }

    @Basic
    @Column(name = "IdCoupled")
    public String getIdCoupled() {
        return idCoupled;
    }

    public void setIdCoupled(String idCoupled) {
        this.idCoupled = idCoupled;
    }

    @Basic
    @Column(name = "IdCoupledT1")
    public String getIdCoupledT1() {
        return idCoupledT1;
    }

    public void setIdCoupledT1(String idCoupledT1) {
        this.idCoupledT1 = idCoupledT1;
    }

    @Basic
    @Column(name = "IdCoupledT2")
    public String getIdCoupledT2() {
        return idCoupledT2;
    }

    public void setIdCoupledT2(String idCoupledT2) {
        this.idCoupledT2 = idCoupledT2;
    }

    @Basic
    @Column(name = "IdCoupledTypeShift")
    public String getIdCoupledTypeShift() {
        return idCoupledTypeShift;
    }

    public void setIdCoupledTypeShift(String idCoupledTypeShift) {
        this.idCoupledTypeShift = idCoupledTypeShift;
    }

    @Basic
    @Column(name = "IdCoupledShift")
    public String getIdCoupledShift() {
        return idCoupledShift;
    }

    public void setIdCoupledShift(String idCoupledShift) {
        this.idCoupledShift = idCoupledShift;
    }

    @Basic
    @Column(name = "IdCoupledGroupResource")
    public String getIdCoupledGroupResource() {
        return idCoupledGroupResource;
    }

    public void setIdCoupledGroupResource(String idCoupledGroupResource) {
        this.idCoupledGroupResource = idCoupledGroupResource;
    }

    @Basic
    @Column(name = "IdCoupledTypeResource")
    public String getIdCoupledTypeResource() {
        return idCoupledTypeResource;
    }

    public void setIdCoupledTypeResource(String idCoupledTypeResource) {
        this.idCoupledTypeResource = idCoupledTypeResource;
    }

    @Basic
    @Column(name = "IdCoupledResouce")
    public String getIdCoupledResouce() {
        return idCoupledResouce;
    }

    public void setIdCoupledResouce(String idCoupledResouce) {
        this.idCoupledResouce = idCoupledResouce;
    }

    @Basic
    @Column(name = "IdCoupledTypeSite")
    public String getIdCoupledTypeSite() {
        return idCoupledTypeSite;
    }

    public void setIdCoupledTypeSite(String idCoupledTypeSite) {
        this.idCoupledTypeSite = idCoupledTypeSite;
    }

    @Basic
    @Column(name = "IdCoupledSite")
    public String getIdCoupledSite() {
        return idCoupledSite;
    }

    public void setIdCoupledSite(String idCoupledSite) {
        this.idCoupledSite = idCoupledSite;
    }

    @Basic
    @Column(name = "Slot1")
    public String getSlot1() {
        return slot1;
    }

    public void setSlot1(String slot1) {
        this.slot1 = slot1;
    }

    @Basic
    @Column(name = "Slot2")
    public String getSlot2() {
        return slot2;
    }

    public void setSlot2(String slot2) {
        this.slot2 = slot2;
    }

    @Basic
    @Column(name = "initTime")
    public Short getInitTime() {
        return initTime;
    }

    public void setInitTime(Short initTime) {
        this.initTime = initTime;
    }

    @Basic
    @Column(name = "unitTime")
    public Short getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(Short unitTime) {
        this.unitTime = unitTime;
    }

    @Basic
    @Column(name = "postTime")
    public Short getPostTime() {
        return postTime;
    }

    public void setPostTime(Short postTime) {
        this.postTime = postTime;
    }

    @Basic
    @Column(name = "checkTime")
    public Short getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Short checkTime) {
        this.checkTime = checkTime;
    }

    @Basic
    @Column(name = "delta")
    public Short getDelta() {
        return delta;
    }

    public void setDelta(Short delta) {
        this.delta = delta;
    }

    @Basic
    @Column(name = "estimate")
    public Short getEstimate() {
        return estimate;
    }

    public void setEstimate(Short estimate) {
        this.estimate = estimate;
    }

    @Basic
    @Column(name = "continuous")
    public String getContinuous() {
        return continuous;
    }

    public void setContinuous(String continuous) {
        this.continuous = continuous;
    }

    @Basic
    @Column(name = "IdExclusive")
    public String getIdExclusive() {
        return idExclusive;
    }

    public void setIdExclusive(String idExclusive) {
        this.idExclusive = idExclusive;
    }

    @Basic
    @Column(name = "IdPrec")
    public String getIdPrec() {
        return idPrec;
    }

    public void setIdPrec(String idPrec) {
        this.idPrec = idPrec;
    }

    @Basic
    @Column(name = "IdSucc")
    public String getIdSucc() {
        return idSucc;
    }

    public void setIdSucc(String idSucc) {
        this.idSucc = idSucc;
    }

    @Basic
    @Column(name = "minTimeSucc")
    public Short getMinTimeSucc() {
        return minTimeSucc;
    }

    public void setMinTimeSucc(Short minTimeSucc) {
        this.minTimeSucc = minTimeSucc;
    }

    @Basic
    @Column(name = "maxTimeSucc")
    public Short getMaxTimeSucc() {
        return maxTimeSucc;
    }

    public void setMaxTimeSucc(Short maxTimeSucc) {
        this.maxTimeSucc = maxTimeSucc;
    }

    @Basic
    @Column(name = "quantity")
    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "unitQuantity")
    public Short getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(Short unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    @Basic
    @Column(name = "minQtySwitch")
    public Short getMinQtySwitch() {
        return minQtySwitch;
    }

    public void setMinQtySwitch(Short minQtySwitch) {
        this.minQtySwitch = minQtySwitch;
    }

    @Basic
    @Column(name = "maxQtySwitch")
    public Short getMaxQtySwitch() {
        return maxQtySwitch;
    }

    public void setMaxQtySwitch(Short maxQtySwitch) {
        this.maxQtySwitch = maxQtySwitch;
    }

    @Basic
    @Column(name = "modQtySwitch")
    public Short getModQtySwitch() {
        return modQtySwitch;
    }

    public void setModQtySwitch(Short modQtySwitch) {
        this.modQtySwitch = modQtySwitch;
    }

    @Basic
    @Column(name = "IdSwitch")
    public String getIdSwitch() {
        return idSwitch;
    }

    public void setIdSwitch(String idSwitch) {
        this.idSwitch = idSwitch;
    }

    @Basic
    @Column(name = "maxResourceDivision")
    public Short getMaxResourceDivision() {
        return maxResourceDivision;
    }

    public void setMaxResourceDivision(Short maxResourceDivision) {
        this.maxResourceDivision = maxResourceDivision;
    }

    @Basic
    @Column(name = "minResourceDivision")
    public Short getMinResourceDivision() {
        return minResourceDivision;
    }

    public void setMinResourceDivision(Short minResourceDivision) {
        this.minResourceDivision = minResourceDivision;
    }

    @Basic
    @Column(name = "modResourceDivision")
    public Short getModResourceDivision() {
        return modResourceDivision;
    }

    public void setModResourceDivision(Short modResourceDivision) {
        this.modResourceDivision = modResourceDivision;
    }

    @Basic
    @Column(name = "minTimeDivision")
    public Short getMinTimeDivision() {
        return minTimeDivision;
    }

    public void setMinTimeDivision(Short minTimeDivision) {
        this.minTimeDivision = minTimeDivision;
    }

    @Basic
    @Column(name = "maxTimeDivision")
    public Short getMaxTimeDivision() {
        return maxTimeDivision;
    }

    public void setMaxTimeDivision(Short maxTimeDivision) {
        this.maxTimeDivision = maxTimeDivision;
    }

    @Basic
    @Column(name = "modTimeDivision")
    public Short getModTimeDivision() {
        return modTimeDivision;
    }

    public void setModTimeDivision(Short modTimeDivision) {
        this.modTimeDivision = modTimeDivision;
    }

    @Basic
    @Column(name = "minQtyDivision")
    public Short getMinQtyDivision() {
        return minQtyDivision;
    }

    public void setMinQtyDivision(Short minQtyDivision) {
        this.minQtyDivision = minQtyDivision;
    }

    @Basic
    @Column(name = "maxQtyDivision")
    public Short getMaxQtyDivision() {
        return maxQtyDivision;
    }

    public void setMaxQtyDivision(Short maxQtyDivision) {
        this.maxQtyDivision = maxQtyDivision;
    }

    @Basic
    @Column(name = "modQtyDivision")
    public Short getModQtyDivision() {
        return modQtyDivision;
    }

    public void setModQtyDivision(Short modQtyDivision) {
        this.modQtyDivision = modQtyDivision;
    }

    @Basic
    @Column(name = "minQtyBatch")
    public Short getMinQtyBatch() {
        return minQtyBatch;
    }

    public void setMinQtyBatch(Short minQtyBatch) {
        this.minQtyBatch = minQtyBatch;
    }

    @Basic
    @Column(name = "maxQtyBatch")
    public Short getMaxQtyBatch() {
        return maxQtyBatch;
    }

    public void setMaxQtyBatch(Short maxQtyBatch) {
        this.maxQtyBatch = maxQtyBatch;
    }

    @Basic
    @Column(name = "modQtyBatch")
    public Short getModQtyBatch() {
        return modQtyBatch;
    }

    public void setModQtyBatch(Short modQtyBatch) {
        this.modQtyBatch = modQtyBatch;
    }

    @Basic
    @Column(name = "minTimeBatch")
    public Short getMinTimeBatch() {
        return minTimeBatch;
    }

    public void setMinTimeBatch(Short minTimeBatch) {
        this.minTimeBatch = minTimeBatch;
    }

    @Basic
    @Column(name = "maxTimeBatch")
    public Short getMaxTimeBatch() {
        return maxTimeBatch;
    }

    public void setMaxTimeBatch(Short maxTimeBatch) {
        this.maxTimeBatch = maxTimeBatch;
    }

    @Basic
    @Column(name = "modTimeBatch")
    public Short getModTimeBatch() {
        return modTimeBatch;
    }

    public void setModTimeBatch(Short modTimeBatch) {
        this.modTimeBatch = modTimeBatch;
    }

    @Basic
    @Column(name = "batch")
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Basic
    @Column(name = "idIcon")
    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    @Basic
    @Column(name = "nbTask")
    public Short getNbTask() {
        return nbTask;
    }

    public void setNbTask(Short nbTask) {
        this.nbTask = nbTask;
    }

    @Basic
    @Column(name = "RG_switch")
    public Short getRgSwitch() {
        return rgSwitch;
    }

    public void setRgSwitch(Short rgSwitch) {
        this.rgSwitch = rgSwitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcessEntity that = (ProcessEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idRoot != null ? !idRoot.equals(that.idRoot) : that.idRoot != null) return false;
        if (idParent != null ? !idParent.equals(that.idParent) : that.idParent != null) return false;
        if (idProduct != null ? !idProduct.equals(that.idProduct) : that.idProduct != null) return false;
        if (ordToRoot != null ? !ordToRoot.equals(that.ordToRoot) : that.ordToRoot != null) return false;
        if (ordToRootChild != null ? !ordToRootChild.equals(that.ordToRootChild) : that.ordToRootChild != null)
            return false;
        if (ordToParent != null ? !ordToParent.equals(that.ordToParent) : that.ordToParent != null) return false;
        if (typeShift != null ? !typeShift.equals(that.typeShift) : that.typeShift != null) return false;
        if (preemptive != null ? !preemptive.equals(that.preemptive) : that.preemptive != null) return false;
        if (exclusiveJob != null ? !exclusiveJob.equals(that.exclusiveJob) : that.exclusiveJob != null) return false;
        if (exclusiveOrder != null ? !exclusiveOrder.equals(that.exclusiveOrder) : that.exclusiveOrder != null)
            return false;
        if (coupledTypeOrder != null ? !coupledTypeOrder.equals(that.coupledTypeOrder) : that.coupledTypeOrder != null)
            return false;
        if (idCoupled != null ? !idCoupled.equals(that.idCoupled) : that.idCoupled != null) return false;
        if (idCoupledT1 != null ? !idCoupledT1.equals(that.idCoupledT1) : that.idCoupledT1 != null) return false;
        if (idCoupledT2 != null ? !idCoupledT2.equals(that.idCoupledT2) : that.idCoupledT2 != null) return false;
        if (idCoupledTypeShift != null ? !idCoupledTypeShift.equals(that.idCoupledTypeShift) : that.idCoupledTypeShift != null)
            return false;
        if (idCoupledShift != null ? !idCoupledShift.equals(that.idCoupledShift) : that.idCoupledShift != null)
            return false;
        if (idCoupledGroupResource != null ? !idCoupledGroupResource.equals(that.idCoupledGroupResource) : that.idCoupledGroupResource != null)
            return false;
        if (idCoupledTypeResource != null ? !idCoupledTypeResource.equals(that.idCoupledTypeResource) : that.idCoupledTypeResource != null)
            return false;
        if (idCoupledResouce != null ? !idCoupledResouce.equals(that.idCoupledResouce) : that.idCoupledResouce != null)
            return false;
        if (idCoupledTypeSite != null ? !idCoupledTypeSite.equals(that.idCoupledTypeSite) : that.idCoupledTypeSite != null)
            return false;
        if (idCoupledSite != null ? !idCoupledSite.equals(that.idCoupledSite) : that.idCoupledSite != null)
            return false;
        if (slot1 != null ? !slot1.equals(that.slot1) : that.slot1 != null) return false;
        if (slot2 != null ? !slot2.equals(that.slot2) : that.slot2 != null) return false;
        if (initTime != null ? !initTime.equals(that.initTime) : that.initTime != null) return false;
        if (unitTime != null ? !unitTime.equals(that.unitTime) : that.unitTime != null) return false;
        if (postTime != null ? !postTime.equals(that.postTime) : that.postTime != null) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;
        if (delta != null ? !delta.equals(that.delta) : that.delta != null) return false;
        if (estimate != null ? !estimate.equals(that.estimate) : that.estimate != null) return false;
        if (continuous != null ? !continuous.equals(that.continuous) : that.continuous != null) return false;
        if (idExclusive != null ? !idExclusive.equals(that.idExclusive) : that.idExclusive != null) return false;
        if (idPrec != null ? !idPrec.equals(that.idPrec) : that.idPrec != null) return false;
        if (idSucc != null ? !idSucc.equals(that.idSucc) : that.idSucc != null) return false;
        if (minTimeSucc != null ? !minTimeSucc.equals(that.minTimeSucc) : that.minTimeSucc != null) return false;
        if (maxTimeSucc != null ? !maxTimeSucc.equals(that.maxTimeSucc) : that.maxTimeSucc != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (unitQuantity != null ? !unitQuantity.equals(that.unitQuantity) : that.unitQuantity != null) return false;
        if (minQtySwitch != null ? !minQtySwitch.equals(that.minQtySwitch) : that.minQtySwitch != null) return false;
        if (maxQtySwitch != null ? !maxQtySwitch.equals(that.maxQtySwitch) : that.maxQtySwitch != null) return false;
        if (modQtySwitch != null ? !modQtySwitch.equals(that.modQtySwitch) : that.modQtySwitch != null) return false;
        if (idSwitch != null ? !idSwitch.equals(that.idSwitch) : that.idSwitch != null) return false;
        if (maxResourceDivision != null ? !maxResourceDivision.equals(that.maxResourceDivision) : that.maxResourceDivision != null)
            return false;
        if (minResourceDivision != null ? !minResourceDivision.equals(that.minResourceDivision) : that.minResourceDivision != null)
            return false;
        if (modResourceDivision != null ? !modResourceDivision.equals(that.modResourceDivision) : that.modResourceDivision != null)
            return false;
        if (minTimeDivision != null ? !minTimeDivision.equals(that.minTimeDivision) : that.minTimeDivision != null)
            return false;
        if (maxTimeDivision != null ? !maxTimeDivision.equals(that.maxTimeDivision) : that.maxTimeDivision != null)
            return false;
        if (modTimeDivision != null ? !modTimeDivision.equals(that.modTimeDivision) : that.modTimeDivision != null)
            return false;
        if (minQtyDivision != null ? !minQtyDivision.equals(that.minQtyDivision) : that.minQtyDivision != null)
            return false;
        if (maxQtyDivision != null ? !maxQtyDivision.equals(that.maxQtyDivision) : that.maxQtyDivision != null)
            return false;
        if (modQtyDivision != null ? !modQtyDivision.equals(that.modQtyDivision) : that.modQtyDivision != null)
            return false;
        if (minQtyBatch != null ? !minQtyBatch.equals(that.minQtyBatch) : that.minQtyBatch != null) return false;
        if (maxQtyBatch != null ? !maxQtyBatch.equals(that.maxQtyBatch) : that.maxQtyBatch != null) return false;
        if (modQtyBatch != null ? !modQtyBatch.equals(that.modQtyBatch) : that.modQtyBatch != null) return false;
        if (minTimeBatch != null ? !minTimeBatch.equals(that.minTimeBatch) : that.minTimeBatch != null) return false;
        if (maxTimeBatch != null ? !maxTimeBatch.equals(that.maxTimeBatch) : that.maxTimeBatch != null) return false;
        if (modTimeBatch != null ? !modTimeBatch.equals(that.modTimeBatch) : that.modTimeBatch != null) return false;
        if (batch != null ? !batch.equals(that.batch) : that.batch != null) return false;
        if (idIcon != null ? !idIcon.equals(that.idIcon) : that.idIcon != null) return false;
        if (nbTask != null ? !nbTask.equals(that.nbTask) : that.nbTask != null) return false;
        if (rgSwitch != null ? !rgSwitch.equals(that.rgSwitch) : that.rgSwitch != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idRoot != null ? idRoot.hashCode() : 0);
        result = 31 * result + (idParent != null ? idParent.hashCode() : 0);
        result = 31 * result + (idProduct != null ? idProduct.hashCode() : 0);
        result = 31 * result + (ordToRoot != null ? ordToRoot.hashCode() : 0);
        result = 31 * result + (ordToRootChild != null ? ordToRootChild.hashCode() : 0);
        result = 31 * result + (ordToParent != null ? ordToParent.hashCode() : 0);
        result = 31 * result + (typeShift != null ? typeShift.hashCode() : 0);
        result = 31 * result + (preemptive != null ? preemptive.hashCode() : 0);
        result = 31 * result + (exclusiveJob != null ? exclusiveJob.hashCode() : 0);
        result = 31 * result + (exclusiveOrder != null ? exclusiveOrder.hashCode() : 0);
        result = 31 * result + (coupledTypeOrder != null ? coupledTypeOrder.hashCode() : 0);
        result = 31 * result + (idCoupled != null ? idCoupled.hashCode() : 0);
        result = 31 * result + (idCoupledT1 != null ? idCoupledT1.hashCode() : 0);
        result = 31 * result + (idCoupledT2 != null ? idCoupledT2.hashCode() : 0);
        result = 31 * result + (idCoupledTypeShift != null ? idCoupledTypeShift.hashCode() : 0);
        result = 31 * result + (idCoupledShift != null ? idCoupledShift.hashCode() : 0);
        result = 31 * result + (idCoupledGroupResource != null ? idCoupledGroupResource.hashCode() : 0);
        result = 31 * result + (idCoupledTypeResource != null ? idCoupledTypeResource.hashCode() : 0);
        result = 31 * result + (idCoupledResouce != null ? idCoupledResouce.hashCode() : 0);
        result = 31 * result + (idCoupledTypeSite != null ? idCoupledTypeSite.hashCode() : 0);
        result = 31 * result + (idCoupledSite != null ? idCoupledSite.hashCode() : 0);
        result = 31 * result + (slot1 != null ? slot1.hashCode() : 0);
        result = 31 * result + (slot2 != null ? slot2.hashCode() : 0);
        result = 31 * result + (initTime != null ? initTime.hashCode() : 0);
        result = 31 * result + (unitTime != null ? unitTime.hashCode() : 0);
        result = 31 * result + (postTime != null ? postTime.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (delta != null ? delta.hashCode() : 0);
        result = 31 * result + (estimate != null ? estimate.hashCode() : 0);
        result = 31 * result + (continuous != null ? continuous.hashCode() : 0);
        result = 31 * result + (idExclusive != null ? idExclusive.hashCode() : 0);
        result = 31 * result + (idPrec != null ? idPrec.hashCode() : 0);
        result = 31 * result + (idSucc != null ? idSucc.hashCode() : 0);
        result = 31 * result + (minTimeSucc != null ? minTimeSucc.hashCode() : 0);
        result = 31 * result + (maxTimeSucc != null ? maxTimeSucc.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (unitQuantity != null ? unitQuantity.hashCode() : 0);
        result = 31 * result + (minQtySwitch != null ? minQtySwitch.hashCode() : 0);
        result = 31 * result + (maxQtySwitch != null ? maxQtySwitch.hashCode() : 0);
        result = 31 * result + (modQtySwitch != null ? modQtySwitch.hashCode() : 0);
        result = 31 * result + (idSwitch != null ? idSwitch.hashCode() : 0);
        result = 31 * result + (maxResourceDivision != null ? maxResourceDivision.hashCode() : 0);
        result = 31 * result + (minResourceDivision != null ? minResourceDivision.hashCode() : 0);
        result = 31 * result + (modResourceDivision != null ? modResourceDivision.hashCode() : 0);
        result = 31 * result + (minTimeDivision != null ? minTimeDivision.hashCode() : 0);
        result = 31 * result + (maxTimeDivision != null ? maxTimeDivision.hashCode() : 0);
        result = 31 * result + (modTimeDivision != null ? modTimeDivision.hashCode() : 0);
        result = 31 * result + (minQtyDivision != null ? minQtyDivision.hashCode() : 0);
        result = 31 * result + (maxQtyDivision != null ? maxQtyDivision.hashCode() : 0);
        result = 31 * result + (modQtyDivision != null ? modQtyDivision.hashCode() : 0);
        result = 31 * result + (minQtyBatch != null ? minQtyBatch.hashCode() : 0);
        result = 31 * result + (maxQtyBatch != null ? maxQtyBatch.hashCode() : 0);
        result = 31 * result + (modQtyBatch != null ? modQtyBatch.hashCode() : 0);
        result = 31 * result + (minTimeBatch != null ? minTimeBatch.hashCode() : 0);
        result = 31 * result + (maxTimeBatch != null ? maxTimeBatch.hashCode() : 0);
        result = 31 * result + (modTimeBatch != null ? modTimeBatch.hashCode() : 0);
        result = 31 * result + (batch != null ? batch.hashCode() : 0);
        result = 31 * result + (idIcon != null ? idIcon.hashCode() : 0);
        result = 31 * result + (nbTask != null ? nbTask.hashCode() : 0);
        result = 31 * result + (rgSwitch != null ? rgSwitch.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "processByIdProcess")
    public Collection<AssisantprocessEntity> getAssisantprocessesById() {
        return assisantprocessesById;
    }

    public void setAssisantprocessesById(Collection<AssisantprocessEntity> assisantprocessesById) {
        this.assisantprocessesById = assisantprocessesById;
    }

    @OneToMany(mappedBy = "processByIdProcess")
    public Collection<PlanEntity> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<PlanEntity> plansById) {
        this.plansById = plansById;
    }

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    public ProductEntity getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(ProductEntity productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }
}
