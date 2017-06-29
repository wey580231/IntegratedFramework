package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

/**
 * Created by wey580231 on 2017/5/23.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_ProcessEntity {
    private String id;
    private String name;
    private String idRoot;
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
    private Short rgSwitch;
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
    private boolean rootProcess;
    private RG_ProductEntity productByIdProduct;
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "processId")
    private RG_ProcessEntity processByIdProcess;
    private Set<RG_ProcessEntity> childProcess;

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

    public String getIdRoot() {
        return idRoot;
    }

    public void setIdRoot(String idRoot) {
        this.idRoot = idRoot;
    }

    public Short getOrdToRoot() {
        return ordToRoot;
    }

    public void setOrdToRoot(Short ordToRoot) {
        this.ordToRoot = ordToRoot;
    }

    public String getOrdToRootChild() {
        return ordToRootChild;
    }

    public void setOrdToRootChild(String ordToRootChild) {
        this.ordToRootChild = ordToRootChild;
    }

    public Short getOrdToParent() {
        return ordToParent;
    }

    public void setOrdToParent(Short ordToParent) {
        this.ordToParent = ordToParent;
    }

    public Byte getTypeShift() {
        return typeShift;
    }

    public void setTypeShift(Byte typeShift) {
        this.typeShift = typeShift;
    }

    public String getPreemptive() {
        return preemptive;
    }

    public void setPreemptive(String preemptive) {
        this.preemptive = preemptive;
    }

    public String getExclusiveJob() {
        return exclusiveJob;
    }

    public void setExclusiveJob(String exclusiveJob) {
        this.exclusiveJob = exclusiveJob;
    }

    public String getExclusiveOrder() {
        return exclusiveOrder;
    }

    public void setExclusiveOrder(String exclusiveOrder) {
        this.exclusiveOrder = exclusiveOrder;
    }

    public String getCoupledTypeOrder() {
        return coupledTypeOrder;
    }

    public void setCoupledTypeOrder(String coupledTypeOrder) {
        this.coupledTypeOrder = coupledTypeOrder;
    }

    public String getIdCoupled() {
        return idCoupled;
    }

    public void setIdCoupled(String idCoupled) {
        this.idCoupled = idCoupled;
    }

    public String getIdCoupledT1() {
        return idCoupledT1;
    }

    public void setIdCoupledT1(String idCoupledT1) {
        this.idCoupledT1 = idCoupledT1;
    }

    public String getIdCoupledT2() {
        return idCoupledT2;
    }

    public void setIdCoupledT2(String idCoupledT2) {
        this.idCoupledT2 = idCoupledT2;
    }

    public String getIdCoupledTypeShift() {
        return idCoupledTypeShift;
    }

    public void setIdCoupledTypeShift(String idCoupledTypeShift) {
        this.idCoupledTypeShift = idCoupledTypeShift;
    }

    public String getIdCoupledShift() {
        return idCoupledShift;
    }

    public void setIdCoupledShift(String idCoupledShift) {
        this.idCoupledShift = idCoupledShift;
    }

    public String getIdCoupledGroupResource() {
        return idCoupledGroupResource;
    }

    public void setIdCoupledGroupResource(String idCoupledGroupResource) {
        this.idCoupledGroupResource = idCoupledGroupResource;
    }

    public String getIdCoupledTypeResource() {
        return idCoupledTypeResource;
    }

    public void setIdCoupledTypeResource(String idCoupledTypeResource) {
        this.idCoupledTypeResource = idCoupledTypeResource;
    }

    public String getIdCoupledResouce() {
        return idCoupledResouce;
    }

    public void setIdCoupledResouce(String idCoupledResouce) {
        this.idCoupledResouce = idCoupledResouce;
    }

    public String getIdCoupledTypeSite() {
        return idCoupledTypeSite;
    }

    public void setIdCoupledTypeSite(String idCoupledTypeSite) {
        this.idCoupledTypeSite = idCoupledTypeSite;
    }

    public String getIdCoupledSite() {
        return idCoupledSite;
    }

    public void setIdCoupledSite(String idCoupledSite) {
        this.idCoupledSite = idCoupledSite;
    }

    public String getSlot1() {
        return slot1;
    }

    public void setSlot1(String slot1) {
        this.slot1 = slot1;
    }

    public String getSlot2() {
        return slot2;
    }

    public void setSlot2(String slot2) {
        this.slot2 = slot2;
    }

    public Short getInitTime() {
        return initTime;
    }

    public void setInitTime(Short initTime) {
        this.initTime = initTime;
    }

    public Short getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(Short unitTime) {
        this.unitTime = unitTime;
    }

    public Short getPostTime() {
        return postTime;
    }

    public void setPostTime(Short postTime) {
        this.postTime = postTime;
    }

    public Short getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Short checkTime) {
        this.checkTime = checkTime;
    }

    public Short getDelta() {
        return delta;
    }

    public void setDelta(Short delta) {
        this.delta = delta;
    }

    public Short getEstimate() {
        return estimate;
    }

    public void setEstimate(Short estimate) {
        this.estimate = estimate;
    }

    public String getContinuous() {
        return continuous;
    }

    public void setContinuous(String continuous) {
        this.continuous = continuous;
    }

    public String getIdExclusive() {
        return idExclusive;
    }

    public void setIdExclusive(String idExclusive) {
        this.idExclusive = idExclusive;
    }

    public String getIdPrec() {
        return idPrec;
    }

    public void setIdPrec(String idPrec) {
        this.idPrec = idPrec;
    }

    public String getIdSucc() {
        return idSucc;
    }

    public void setIdSucc(String idSucc) {
        this.idSucc = idSucc;
    }

    public Short getMinTimeSucc() {
        return minTimeSucc;
    }

    public void setMinTimeSucc(Short minTimeSucc) {
        this.minTimeSucc = minTimeSucc;
    }

    public Short getMaxTimeSucc() {
        return maxTimeSucc;
    }

    public void setMaxTimeSucc(Short maxTimeSucc) {
        this.maxTimeSucc = maxTimeSucc;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Short getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(Short unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public Short getRgSwitch() {
        return rgSwitch;
    }

    public void setRgSwitch(Short rgSwitch) {
        this.rgSwitch = rgSwitch;
    }

    public Short getMinQtySwitch() {
        return minQtySwitch;
    }

    public void setMinQtySwitch(Short minQtySwitch) {
        this.minQtySwitch = minQtySwitch;
    }

    public Short getMaxQtySwitch() {
        return maxQtySwitch;
    }

    public void setMaxQtySwitch(Short maxQtySwitch) {
        this.maxQtySwitch = maxQtySwitch;
    }

    public Short getModQtySwitch() {
        return modQtySwitch;
    }

    public void setModQtySwitch(Short modQtySwitch) {
        this.modQtySwitch = modQtySwitch;
    }

    public String getIdSwitch() {
        return idSwitch;
    }

    public void setIdSwitch(String idSwitch) {
        this.idSwitch = idSwitch;
    }

    public Short getMaxResourceDivision() {
        return maxResourceDivision;
    }

    public void setMaxResourceDivision(Short maxResourceDivision) {
        this.maxResourceDivision = maxResourceDivision;
    }

    public Short getMinResourceDivision() {
        return minResourceDivision;
    }

    public void setMinResourceDivision(Short minResourceDivision) {
        this.minResourceDivision = minResourceDivision;
    }

    public Short getModResourceDivision() {
        return modResourceDivision;
    }

    public void setModResourceDivision(Short modResourceDivision) {
        this.modResourceDivision = modResourceDivision;
    }

    public Short getMinTimeDivision() {
        return minTimeDivision;
    }

    public void setMinTimeDivision(Short minTimeDivision) {
        this.minTimeDivision = minTimeDivision;
    }

    public Short getMaxTimeDivision() {
        return maxTimeDivision;
    }

    public void setMaxTimeDivision(Short maxTimeDivision) {
        this.maxTimeDivision = maxTimeDivision;
    }

    public Short getModTimeDivision() {
        return modTimeDivision;
    }

    public void setModTimeDivision(Short modTimeDivision) {
        this.modTimeDivision = modTimeDivision;
    }

    public Short getMinQtyDivision() {
        return minQtyDivision;
    }

    public void setMinQtyDivision(Short minQtyDivision) {
        this.minQtyDivision = minQtyDivision;
    }

    public Short getMaxQtyDivision() {
        return maxQtyDivision;
    }

    public void setMaxQtyDivision(Short maxQtyDivision) {
        this.maxQtyDivision = maxQtyDivision;
    }

    public Short getModQtyDivision() {
        return modQtyDivision;
    }

    public void setModQtyDivision(Short modQtyDivision) {
        this.modQtyDivision = modQtyDivision;
    }

    public Short getMinQtyBatch() {
        return minQtyBatch;
    }

    public void setMinQtyBatch(Short minQtyBatch) {
        this.minQtyBatch = minQtyBatch;
    }

    public Short getMaxQtyBatch() {
        return maxQtyBatch;
    }

    public void setMaxQtyBatch(Short maxQtyBatch) {
        this.maxQtyBatch = maxQtyBatch;
    }

    public Short getModQtyBatch() {
        return modQtyBatch;
    }

    public void setModQtyBatch(Short modQtyBatch) {
        this.modQtyBatch = modQtyBatch;
    }

    public Short getMinTimeBatch() {
        return minTimeBatch;
    }

    public void setMinTimeBatch(Short minTimeBatch) {
        this.minTimeBatch = minTimeBatch;
    }

    public Short getMaxTimeBatch() {
        return maxTimeBatch;
    }

    public void setMaxTimeBatch(Short maxTimeBatch) {
        this.maxTimeBatch = maxTimeBatch;
    }

    public Short getModTimeBatch() {
        return modTimeBatch;
    }

    public void setModTimeBatch(Short modTimeBatch) {
        this.modTimeBatch = modTimeBatch;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    public Short getNbTask() {
        return nbTask;
    }

    public void setNbTask(Short nbTask) {
        this.nbTask = nbTask;
    }

    public boolean isRootProcess() {
        return rootProcess;
    }

    public void setRootProcess(boolean rootProcess) {
        this.rootProcess = rootProcess;
    }

    public RG_ProductEntity getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(RG_ProductEntity productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }

    public RG_ProcessEntity getProcessByIdProcess() {
        return processByIdProcess;
    }

    public void setProcessByIdProcess(RG_ProcessEntity processByIdProcess) {
        this.processByIdProcess = processByIdProcess;
    }

    public Set<RG_ProcessEntity> getChildProcess() {
        return childProcess;
    }

    public void setChildProcess(Set<RG_ProcessEntity> childProcess) {
        this.childProcess = childProcess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ProcessEntity that = (RG_ProcessEntity) o;

        if (rootProcess != that.rootProcess) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idRoot != null ? !idRoot.equals(that.idRoot) : that.idRoot != null) return false;
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
        if (rgSwitch != null ? !rgSwitch.equals(that.rgSwitch) : that.rgSwitch != null) return false;
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
        return nbTask != null ? nbTask.equals(that.nbTask) : that.nbTask == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idRoot != null ? idRoot.hashCode() : 0);
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
        result = 31 * result + (rgSwitch != null ? rgSwitch.hashCode() : 0);
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
        result = 31 * result + (rootProcess ? 1 : 0);
        return result;
    }
}
