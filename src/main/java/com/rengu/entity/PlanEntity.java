package com.rengu.entity;

import javax.persistence.*;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "plan", schema = "testdatabase", catalog = "")
public class PlanEntity {
    private String idTask;
    private String idJob;
    private String idProductOrder;
    private String nameTask;
    private String nameProcess;
    private String nameOrder;
    private String nameJob;
    private String nameResource;
    private String nameGroupResource;
    private String nameTypeResource;
    private String nameSite;
    private String nameProductOrder;
    private String nameProvider;
    private Short ordToParentTask;
    private String idTaskResourceSucc;
    private String preemptiveTask;
    private String divisibleTask;
    private String continuousTask;
    private String idTaskGroupTypeResourceSucc;
    private Short quantityTask;
    private Short quantityResourceTask;
    private Short quantityBatchTask;
    private Short qtySequence;
    private String t1Task;
    private String t2Task;
    private String t2ExtendedTask;
    private String advice;
    private String calendarTask;
    private String slotTask;
    private String estimateTask;
    private String timeTask;
    private String initTimeTask;
    private String unitTimeTask;
    private String postTimeTask;
    private String checkTimeTask;
    private String idGroupResource0Task;
    private String idResource0Task;
    private String idSite0Task;
    private Short quantity0Task;
    private String t10Task;
    private String t20Task;
    private String t20ExtendedTask;
    private String t1RealTask;
    private String t2RealTask;
    private String t1Job;
    private String t2Job;
    private Short quantityJob;
    private Short quantityProviderTask;
    private Short nbTaskJob;
    private String refProductJob;
    private Short ordToRootJob;
    private String ordToRootChildJob;
    private Short minQtyBatchTask;
    private Short minTimeBatchTask;
    private String t1Order;
    private String t2Order;
    private Short quantityOrder;
    private Short priorityOrder;
    private String colorOrder;
    private Byte state;
    private Byte selected;
    private Short timeTypeResourceProvider;
    private Short timeSlotTypeResourceProvider;
    private Short capacitySlotGroupTypeResource;
    private int id;
    private ClubEntity clubByIdClub;
    private ProcessEntity processByIdProcess;
    private OrderEntity orderByIdOrder;
    private ResourceEntity resourceByIdResource;
    private SiteEntity siteByIdSite;
    private GroupresourceEntity groupresourceByIdGroupResource;
    private TyperescourceEntity typerescourceByIdTypeResource;
    private ProviderEntity providerByIdProvider;

    @Basic
    @Column(name = "idTask")
    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    @Basic
    @Column(name = "idJob")
    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    @Basic
    @Column(name = "idProductOrder")
    public String getIdProductOrder() {
        return idProductOrder;
    }

    public void setIdProductOrder(String idProductOrder) {
        this.idProductOrder = idProductOrder;
    }

    @Basic
    @Column(name = "nameTask")
    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    @Basic
    @Column(name = "nameProcess")
    public String getNameProcess() {
        return nameProcess;
    }

    public void setNameProcess(String nameProcess) {
        this.nameProcess = nameProcess;
    }

    @Basic
    @Column(name = "nameOrder")
    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    @Basic
    @Column(name = "nameJob")
    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    @Basic
    @Column(name = "nameResource")
    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    @Basic
    @Column(name = "nameGroupResource")
    public String getNameGroupResource() {
        return nameGroupResource;
    }

    public void setNameGroupResource(String nameGroupResource) {
        this.nameGroupResource = nameGroupResource;
    }

    @Basic
    @Column(name = "nameTypeResource")
    public String getNameTypeResource() {
        return nameTypeResource;
    }

    public void setNameTypeResource(String nameTypeResource) {
        this.nameTypeResource = nameTypeResource;
    }

    @Basic
    @Column(name = "nameSite")
    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    @Basic
    @Column(name = "nameProductOrder")
    public String getNameProductOrder() {
        return nameProductOrder;
    }

    public void setNameProductOrder(String nameProductOrder) {
        this.nameProductOrder = nameProductOrder;
    }

    @Basic
    @Column(name = "nameProvider")
    public String getNameProvider() {
        return nameProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    @Basic
    @Column(name = "ordToParentTask")
    public Short getOrdToParentTask() {
        return ordToParentTask;
    }

    public void setOrdToParentTask(Short ordToParentTask) {
        this.ordToParentTask = ordToParentTask;
    }

    @Basic
    @Column(name = "idTaskResourceSucc")
    public String getIdTaskResourceSucc() {
        return idTaskResourceSucc;
    }

    public void setIdTaskResourceSucc(String idTaskResourceSucc) {
        this.idTaskResourceSucc = idTaskResourceSucc;
    }

    @Basic
    @Column(name = "preemptiveTask")
    public String getPreemptiveTask() {
        return preemptiveTask;
    }

    public void setPreemptiveTask(String preemptiveTask) {
        this.preemptiveTask = preemptiveTask;
    }

    @Basic
    @Column(name = "divisibleTask")
    public String getDivisibleTask() {
        return divisibleTask;
    }

    public void setDivisibleTask(String divisibleTask) {
        this.divisibleTask = divisibleTask;
    }

    @Basic
    @Column(name = "continuousTask")
    public String getContinuousTask() {
        return continuousTask;
    }

    public void setContinuousTask(String continuousTask) {
        this.continuousTask = continuousTask;
    }

    @Basic
    @Column(name = "idTaskGroupTypeResourceSucc")
    public String getIdTaskGroupTypeResourceSucc() {
        return idTaskGroupTypeResourceSucc;
    }

    public void setIdTaskGroupTypeResourceSucc(String idTaskGroupTypeResourceSucc) {
        this.idTaskGroupTypeResourceSucc = idTaskGroupTypeResourceSucc;
    }

    @Basic
    @Column(name = "quantityTask")
    public Short getQuantityTask() {
        return quantityTask;
    }

    public void setQuantityTask(Short quantityTask) {
        this.quantityTask = quantityTask;
    }

    @Basic
    @Column(name = "quantityResourceTask")
    public Short getQuantityResourceTask() {
        return quantityResourceTask;
    }

    public void setQuantityResourceTask(Short quantityResourceTask) {
        this.quantityResourceTask = quantityResourceTask;
    }

    @Basic
    @Column(name = "quantityBatchTask")
    public Short getQuantityBatchTask() {
        return quantityBatchTask;
    }

    public void setQuantityBatchTask(Short quantityBatchTask) {
        this.quantityBatchTask = quantityBatchTask;
    }

    @Basic
    @Column(name = "qtySequence")
    public Short getQtySequence() {
        return qtySequence;
    }

    public void setQtySequence(Short qtySequence) {
        this.qtySequence = qtySequence;
    }

    @Basic
    @Column(name = "t1Task")
    public String getT1Task() {
        return t1Task;
    }

    public void setT1Task(String t1Task) {
        this.t1Task = t1Task;
    }

    @Basic
    @Column(name = "t2Task")
    public String getT2Task() {
        return t2Task;
    }

    public void setT2Task(String t2Task) {
        this.t2Task = t2Task;
    }

    @Basic
    @Column(name = "t2ExtendedTask")
    public String getT2ExtendedTask() {
        return t2ExtendedTask;
    }

    public void setT2ExtendedTask(String t2ExtendedTask) {
        this.t2ExtendedTask = t2ExtendedTask;
    }

    @Basic
    @Column(name = "advice")
    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Basic
    @Column(name = "CalendarTask")
    public String getCalendarTask() {
        return calendarTask;
    }

    public void setCalendarTask(String calendarTask) {
        this.calendarTask = calendarTask;
    }

    @Basic
    @Column(name = "SlotTask")
    public String getSlotTask() {
        return slotTask;
    }

    public void setSlotTask(String slotTask) {
        this.slotTask = slotTask;
    }

    @Basic
    @Column(name = "estimateTask")
    public String getEstimateTask() {
        return estimateTask;
    }

    public void setEstimateTask(String estimateTask) {
        this.estimateTask = estimateTask;
    }

    @Basic
    @Column(name = "timeTask")
    public String getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(String timeTask) {
        this.timeTask = timeTask;
    }

    @Basic
    @Column(name = "initTimeTask")
    public String getInitTimeTask() {
        return initTimeTask;
    }

    public void setInitTimeTask(String initTimeTask) {
        this.initTimeTask = initTimeTask;
    }

    @Basic
    @Column(name = "unitTimeTask")
    public String getUnitTimeTask() {
        return unitTimeTask;
    }

    public void setUnitTimeTask(String unitTimeTask) {
        this.unitTimeTask = unitTimeTask;
    }

    @Basic
    @Column(name = "postTimeTask")
    public String getPostTimeTask() {
        return postTimeTask;
    }

    public void setPostTimeTask(String postTimeTask) {
        this.postTimeTask = postTimeTask;
    }

    @Basic
    @Column(name = "checkTimeTask")
    public String getCheckTimeTask() {
        return checkTimeTask;
    }

    public void setCheckTimeTask(String checkTimeTask) {
        this.checkTimeTask = checkTimeTask;
    }

    @Basic
    @Column(name = "idGroupResource0Task")
    public String getIdGroupResource0Task() {
        return idGroupResource0Task;
    }

    public void setIdGroupResource0Task(String idGroupResource0Task) {
        this.idGroupResource0Task = idGroupResource0Task;
    }

    @Basic
    @Column(name = "idResource0Task")
    public String getIdResource0Task() {
        return idResource0Task;
    }

    public void setIdResource0Task(String idResource0Task) {
        this.idResource0Task = idResource0Task;
    }

    @Basic
    @Column(name = "idSite0Task")
    public String getIdSite0Task() {
        return idSite0Task;
    }

    public void setIdSite0Task(String idSite0Task) {
        this.idSite0Task = idSite0Task;
    }

    @Basic
    @Column(name = "quantity0Task")
    public Short getQuantity0Task() {
        return quantity0Task;
    }

    public void setQuantity0Task(Short quantity0Task) {
        this.quantity0Task = quantity0Task;
    }

    @Basic
    @Column(name = "t10Task")
    public String getT10Task() {
        return t10Task;
    }

    public void setT10Task(String t10Task) {
        this.t10Task = t10Task;
    }

    @Basic
    @Column(name = "t20Task")
    public String getT20Task() {
        return t20Task;
    }

    public void setT20Task(String t20Task) {
        this.t20Task = t20Task;
    }

    @Basic
    @Column(name = "t20ExtendedTask")
    public String getT20ExtendedTask() {
        return t20ExtendedTask;
    }

    public void setT20ExtendedTask(String t20ExtendedTask) {
        this.t20ExtendedTask = t20ExtendedTask;
    }

    @Basic
    @Column(name = "t1RealTask")
    public String getT1RealTask() {
        return t1RealTask;
    }

    public void setT1RealTask(String t1RealTask) {
        this.t1RealTask = t1RealTask;
    }

    @Basic
    @Column(name = "t2RealTask")
    public String getT2RealTask() {
        return t2RealTask;
    }

    public void setT2RealTask(String t2RealTask) {
        this.t2RealTask = t2RealTask;
    }

    @Basic
    @Column(name = "t1Job")
    public String getT1Job() {
        return t1Job;
    }

    public void setT1Job(String t1Job) {
        this.t1Job = t1Job;
    }

    @Basic
    @Column(name = "t2Job")
    public String getT2Job() {
        return t2Job;
    }

    public void setT2Job(String t2Job) {
        this.t2Job = t2Job;
    }

    @Basic
    @Column(name = "quantityJob")
    public Short getQuantityJob() {
        return quantityJob;
    }

    public void setQuantityJob(Short quantityJob) {
        this.quantityJob = quantityJob;
    }

    @Basic
    @Column(name = "quantityProviderTask")
    public Short getQuantityProviderTask() {
        return quantityProviderTask;
    }

    public void setQuantityProviderTask(Short quantityProviderTask) {
        this.quantityProviderTask = quantityProviderTask;
    }

    @Basic
    @Column(name = "nbTaskJob")
    public Short getNbTaskJob() {
        return nbTaskJob;
    }

    public void setNbTaskJob(Short nbTaskJob) {
        this.nbTaskJob = nbTaskJob;
    }

    @Basic
    @Column(name = "refProductJob")
    public String getRefProductJob() {
        return refProductJob;
    }

    public void setRefProductJob(String refProductJob) {
        this.refProductJob = refProductJob;
    }

    @Basic
    @Column(name = "ordToRootJob")
    public Short getOrdToRootJob() {
        return ordToRootJob;
    }

    public void setOrdToRootJob(Short ordToRootJob) {
        this.ordToRootJob = ordToRootJob;
    }

    @Basic
    @Column(name = "OrdToRootChildJob")
    public String getOrdToRootChildJob() {
        return ordToRootChildJob;
    }

    public void setOrdToRootChildJob(String ordToRootChildJob) {
        this.ordToRootChildJob = ordToRootChildJob;
    }

    @Basic
    @Column(name = "minQtyBatchTask")
    public Short getMinQtyBatchTask() {
        return minQtyBatchTask;
    }

    public void setMinQtyBatchTask(Short minQtyBatchTask) {
        this.minQtyBatchTask = minQtyBatchTask;
    }

    @Basic
    @Column(name = "minTimeBatchTask")
    public Short getMinTimeBatchTask() {
        return minTimeBatchTask;
    }

    public void setMinTimeBatchTask(Short minTimeBatchTask) {
        this.minTimeBatchTask = minTimeBatchTask;
    }

    @Basic
    @Column(name = "t1Order")
    public String getT1Order() {
        return t1Order;
    }

    public void setT1Order(String t1Order) {
        this.t1Order = t1Order;
    }

    @Basic
    @Column(name = "t2Order")
    public String getT2Order() {
        return t2Order;
    }

    public void setT2Order(String t2Order) {
        this.t2Order = t2Order;
    }

    @Basic
    @Column(name = "quantityOrder")
    public Short getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Short quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    @Basic
    @Column(name = "priorityOrder")
    public Short getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(Short priorityOrder) {
        this.priorityOrder = priorityOrder;
    }

    @Basic
    @Column(name = "colorOrder")
    public String getColorOrder() {
        return colorOrder;
    }

    public void setColorOrder(String colorOrder) {
        this.colorOrder = colorOrder;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "selected")
    public Byte getSelected() {
        return selected;
    }

    public void setSelected(Byte selected) {
        this.selected = selected;
    }

    @Basic
    @Column(name = "timeTypeResourceProvider")
    public Short getTimeTypeResourceProvider() {
        return timeTypeResourceProvider;
    }

    public void setTimeTypeResourceProvider(Short timeTypeResourceProvider) {
        this.timeTypeResourceProvider = timeTypeResourceProvider;
    }

    @Basic
    @Column(name = "timeSlotTypeResourceProvider")
    public Short getTimeSlotTypeResourceProvider() {
        return timeSlotTypeResourceProvider;
    }

    public void setTimeSlotTypeResourceProvider(Short timeSlotTypeResourceProvider) {
        this.timeSlotTypeResourceProvider = timeSlotTypeResourceProvider;
    }

    @Basic
    @Column(name = "capacitySlotGroupTypeResource")
    public Short getCapacitySlotGroupTypeResource() {
        return capacitySlotGroupTypeResource;
    }

    public void setCapacitySlotGroupTypeResource(Short capacitySlotGroupTypeResource) {
        this.capacitySlotGroupTypeResource = capacitySlotGroupTypeResource;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanEntity that = (PlanEntity) o;

        if (id != that.id) return false;
        if (idTask != null ? !idTask.equals(that.idTask) : that.idTask != null) return false;
        if (idJob != null ? !idJob.equals(that.idJob) : that.idJob != null) return false;
        if (idProductOrder != null ? !idProductOrder.equals(that.idProductOrder) : that.idProductOrder != null)
            return false;
        if (nameTask != null ? !nameTask.equals(that.nameTask) : that.nameTask != null) return false;
        if (nameProcess != null ? !nameProcess.equals(that.nameProcess) : that.nameProcess != null) return false;
        if (nameOrder != null ? !nameOrder.equals(that.nameOrder) : that.nameOrder != null) return false;
        if (nameJob != null ? !nameJob.equals(that.nameJob) : that.nameJob != null) return false;
        if (nameResource != null ? !nameResource.equals(that.nameResource) : that.nameResource != null) return false;
        if (nameGroupResource != null ? !nameGroupResource.equals(that.nameGroupResource) : that.nameGroupResource != null)
            return false;
        if (nameTypeResource != null ? !nameTypeResource.equals(that.nameTypeResource) : that.nameTypeResource != null)
            return false;
        if (nameSite != null ? !nameSite.equals(that.nameSite) : that.nameSite != null) return false;
        if (nameProductOrder != null ? !nameProductOrder.equals(that.nameProductOrder) : that.nameProductOrder != null)
            return false;
        if (nameProvider != null ? !nameProvider.equals(that.nameProvider) : that.nameProvider != null) return false;
        if (ordToParentTask != null ? !ordToParentTask.equals(that.ordToParentTask) : that.ordToParentTask != null)
            return false;
        if (idTaskResourceSucc != null ? !idTaskResourceSucc.equals(that.idTaskResourceSucc) : that.idTaskResourceSucc != null)
            return false;
        if (preemptiveTask != null ? !preemptiveTask.equals(that.preemptiveTask) : that.preemptiveTask != null)
            return false;
        if (divisibleTask != null ? !divisibleTask.equals(that.divisibleTask) : that.divisibleTask != null)
            return false;
        if (continuousTask != null ? !continuousTask.equals(that.continuousTask) : that.continuousTask != null)
            return false;
        if (idTaskGroupTypeResourceSucc != null ? !idTaskGroupTypeResourceSucc.equals(that.idTaskGroupTypeResourceSucc) : that.idTaskGroupTypeResourceSucc != null)
            return false;
        if (quantityTask != null ? !quantityTask.equals(that.quantityTask) : that.quantityTask != null) return false;
        if (quantityResourceTask != null ? !quantityResourceTask.equals(that.quantityResourceTask) : that.quantityResourceTask != null)
            return false;
        if (quantityBatchTask != null ? !quantityBatchTask.equals(that.quantityBatchTask) : that.quantityBatchTask != null)
            return false;
        if (qtySequence != null ? !qtySequence.equals(that.qtySequence) : that.qtySequence != null) return false;
        if (t1Task != null ? !t1Task.equals(that.t1Task) : that.t1Task != null) return false;
        if (t2Task != null ? !t2Task.equals(that.t2Task) : that.t2Task != null) return false;
        if (t2ExtendedTask != null ? !t2ExtendedTask.equals(that.t2ExtendedTask) : that.t2ExtendedTask != null)
            return false;
        if (advice != null ? !advice.equals(that.advice) : that.advice != null) return false;
        if (calendarTask != null ? !calendarTask.equals(that.calendarTask) : that.calendarTask != null) return false;
        if (slotTask != null ? !slotTask.equals(that.slotTask) : that.slotTask != null) return false;
        if (estimateTask != null ? !estimateTask.equals(that.estimateTask) : that.estimateTask != null) return false;
        if (timeTask != null ? !timeTask.equals(that.timeTask) : that.timeTask != null) return false;
        if (initTimeTask != null ? !initTimeTask.equals(that.initTimeTask) : that.initTimeTask != null) return false;
        if (unitTimeTask != null ? !unitTimeTask.equals(that.unitTimeTask) : that.unitTimeTask != null) return false;
        if (postTimeTask != null ? !postTimeTask.equals(that.postTimeTask) : that.postTimeTask != null) return false;
        if (checkTimeTask != null ? !checkTimeTask.equals(that.checkTimeTask) : that.checkTimeTask != null)
            return false;
        if (idGroupResource0Task != null ? !idGroupResource0Task.equals(that.idGroupResource0Task) : that.idGroupResource0Task != null)
            return false;
        if (idResource0Task != null ? !idResource0Task.equals(that.idResource0Task) : that.idResource0Task != null)
            return false;
        if (idSite0Task != null ? !idSite0Task.equals(that.idSite0Task) : that.idSite0Task != null) return false;
        if (quantity0Task != null ? !quantity0Task.equals(that.quantity0Task) : that.quantity0Task != null)
            return false;
        if (t10Task != null ? !t10Task.equals(that.t10Task) : that.t10Task != null) return false;
        if (t20Task != null ? !t20Task.equals(that.t20Task) : that.t20Task != null) return false;
        if (t20ExtendedTask != null ? !t20ExtendedTask.equals(that.t20ExtendedTask) : that.t20ExtendedTask != null)
            return false;
        if (t1RealTask != null ? !t1RealTask.equals(that.t1RealTask) : that.t1RealTask != null) return false;
        if (t2RealTask != null ? !t2RealTask.equals(that.t2RealTask) : that.t2RealTask != null) return false;
        if (t1Job != null ? !t1Job.equals(that.t1Job) : that.t1Job != null) return false;
        if (t2Job != null ? !t2Job.equals(that.t2Job) : that.t2Job != null) return false;
        if (quantityJob != null ? !quantityJob.equals(that.quantityJob) : that.quantityJob != null) return false;
        if (quantityProviderTask != null ? !quantityProviderTask.equals(that.quantityProviderTask) : that.quantityProviderTask != null)
            return false;
        if (nbTaskJob != null ? !nbTaskJob.equals(that.nbTaskJob) : that.nbTaskJob != null) return false;
        if (refProductJob != null ? !refProductJob.equals(that.refProductJob) : that.refProductJob != null)
            return false;
        if (ordToRootJob != null ? !ordToRootJob.equals(that.ordToRootJob) : that.ordToRootJob != null) return false;
        if (ordToRootChildJob != null ? !ordToRootChildJob.equals(that.ordToRootChildJob) : that.ordToRootChildJob != null)
            return false;
        if (minQtyBatchTask != null ? !minQtyBatchTask.equals(that.minQtyBatchTask) : that.minQtyBatchTask != null)
            return false;
        if (minTimeBatchTask != null ? !minTimeBatchTask.equals(that.minTimeBatchTask) : that.minTimeBatchTask != null)
            return false;
        if (t1Order != null ? !t1Order.equals(that.t1Order) : that.t1Order != null) return false;
        if (t2Order != null ? !t2Order.equals(that.t2Order) : that.t2Order != null) return false;
        if (quantityOrder != null ? !quantityOrder.equals(that.quantityOrder) : that.quantityOrder != null)
            return false;
        if (priorityOrder != null ? !priorityOrder.equals(that.priorityOrder) : that.priorityOrder != null)
            return false;
        if (colorOrder != null ? !colorOrder.equals(that.colorOrder) : that.colorOrder != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (selected != null ? !selected.equals(that.selected) : that.selected != null) return false;
        if (timeTypeResourceProvider != null ? !timeTypeResourceProvider.equals(that.timeTypeResourceProvider) : that.timeTypeResourceProvider != null)
            return false;
        if (timeSlotTypeResourceProvider != null ? !timeSlotTypeResourceProvider.equals(that.timeSlotTypeResourceProvider) : that.timeSlotTypeResourceProvider != null)
            return false;
        if (capacitySlotGroupTypeResource != null ? !capacitySlotGroupTypeResource.equals(that.capacitySlotGroupTypeResource) : that.capacitySlotGroupTypeResource != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTask != null ? idTask.hashCode() : 0;
        result = 31 * result + (idJob != null ? idJob.hashCode() : 0);
        result = 31 * result + (idProductOrder != null ? idProductOrder.hashCode() : 0);
        result = 31 * result + (nameTask != null ? nameTask.hashCode() : 0);
        result = 31 * result + (nameProcess != null ? nameProcess.hashCode() : 0);
        result = 31 * result + (nameOrder != null ? nameOrder.hashCode() : 0);
        result = 31 * result + (nameJob != null ? nameJob.hashCode() : 0);
        result = 31 * result + (nameResource != null ? nameResource.hashCode() : 0);
        result = 31 * result + (nameGroupResource != null ? nameGroupResource.hashCode() : 0);
        result = 31 * result + (nameTypeResource != null ? nameTypeResource.hashCode() : 0);
        result = 31 * result + (nameSite != null ? nameSite.hashCode() : 0);
        result = 31 * result + (nameProductOrder != null ? nameProductOrder.hashCode() : 0);
        result = 31 * result + (nameProvider != null ? nameProvider.hashCode() : 0);
        result = 31 * result + (ordToParentTask != null ? ordToParentTask.hashCode() : 0);
        result = 31 * result + (idTaskResourceSucc != null ? idTaskResourceSucc.hashCode() : 0);
        result = 31 * result + (preemptiveTask != null ? preemptiveTask.hashCode() : 0);
        result = 31 * result + (divisibleTask != null ? divisibleTask.hashCode() : 0);
        result = 31 * result + (continuousTask != null ? continuousTask.hashCode() : 0);
        result = 31 * result + (idTaskGroupTypeResourceSucc != null ? idTaskGroupTypeResourceSucc.hashCode() : 0);
        result = 31 * result + (quantityTask != null ? quantityTask.hashCode() : 0);
        result = 31 * result + (quantityResourceTask != null ? quantityResourceTask.hashCode() : 0);
        result = 31 * result + (quantityBatchTask != null ? quantityBatchTask.hashCode() : 0);
        result = 31 * result + (qtySequence != null ? qtySequence.hashCode() : 0);
        result = 31 * result + (t1Task != null ? t1Task.hashCode() : 0);
        result = 31 * result + (t2Task != null ? t2Task.hashCode() : 0);
        result = 31 * result + (t2ExtendedTask != null ? t2ExtendedTask.hashCode() : 0);
        result = 31 * result + (advice != null ? advice.hashCode() : 0);
        result = 31 * result + (calendarTask != null ? calendarTask.hashCode() : 0);
        result = 31 * result + (slotTask != null ? slotTask.hashCode() : 0);
        result = 31 * result + (estimateTask != null ? estimateTask.hashCode() : 0);
        result = 31 * result + (timeTask != null ? timeTask.hashCode() : 0);
        result = 31 * result + (initTimeTask != null ? initTimeTask.hashCode() : 0);
        result = 31 * result + (unitTimeTask != null ? unitTimeTask.hashCode() : 0);
        result = 31 * result + (postTimeTask != null ? postTimeTask.hashCode() : 0);
        result = 31 * result + (checkTimeTask != null ? checkTimeTask.hashCode() : 0);
        result = 31 * result + (idGroupResource0Task != null ? idGroupResource0Task.hashCode() : 0);
        result = 31 * result + (idResource0Task != null ? idResource0Task.hashCode() : 0);
        result = 31 * result + (idSite0Task != null ? idSite0Task.hashCode() : 0);
        result = 31 * result + (quantity0Task != null ? quantity0Task.hashCode() : 0);
        result = 31 * result + (t10Task != null ? t10Task.hashCode() : 0);
        result = 31 * result + (t20Task != null ? t20Task.hashCode() : 0);
        result = 31 * result + (t20ExtendedTask != null ? t20ExtendedTask.hashCode() : 0);
        result = 31 * result + (t1RealTask != null ? t1RealTask.hashCode() : 0);
        result = 31 * result + (t2RealTask != null ? t2RealTask.hashCode() : 0);
        result = 31 * result + (t1Job != null ? t1Job.hashCode() : 0);
        result = 31 * result + (t2Job != null ? t2Job.hashCode() : 0);
        result = 31 * result + (quantityJob != null ? quantityJob.hashCode() : 0);
        result = 31 * result + (quantityProviderTask != null ? quantityProviderTask.hashCode() : 0);
        result = 31 * result + (nbTaskJob != null ? nbTaskJob.hashCode() : 0);
        result = 31 * result + (refProductJob != null ? refProductJob.hashCode() : 0);
        result = 31 * result + (ordToRootJob != null ? ordToRootJob.hashCode() : 0);
        result = 31 * result + (ordToRootChildJob != null ? ordToRootChildJob.hashCode() : 0);
        result = 31 * result + (minQtyBatchTask != null ? minQtyBatchTask.hashCode() : 0);
        result = 31 * result + (minTimeBatchTask != null ? minTimeBatchTask.hashCode() : 0);
        result = 31 * result + (t1Order != null ? t1Order.hashCode() : 0);
        result = 31 * result + (t2Order != null ? t2Order.hashCode() : 0);
        result = 31 * result + (quantityOrder != null ? quantityOrder.hashCode() : 0);
        result = 31 * result + (priorityOrder != null ? priorityOrder.hashCode() : 0);
        result = 31 * result + (colorOrder != null ? colorOrder.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (selected != null ? selected.hashCode() : 0);
        result = 31 * result + (timeTypeResourceProvider != null ? timeTypeResourceProvider.hashCode() : 0);
        result = 31 * result + (timeSlotTypeResourceProvider != null ? timeSlotTypeResourceProvider.hashCode() : 0);
        result = 31 * result + (capacitySlotGroupTypeResource != null ? capacitySlotGroupTypeResource.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idClub", referencedColumnName = "id")
    public ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }

    @ManyToOne
    @JoinColumn(name = "idProcess", referencedColumnName = "id")
    public ProcessEntity getProcessByIdProcess() {
        return processByIdProcess;
    }

    public void setProcessByIdProcess(ProcessEntity processByIdProcess) {
        this.processByIdProcess = processByIdProcess;
    }

    @ManyToOne
    @JoinColumn(name = "idOrder", referencedColumnName = "id")
    public OrderEntity getOrderByIdOrder() {
        return orderByIdOrder;
    }

    public void setOrderByIdOrder(OrderEntity orderByIdOrder) {
        this.orderByIdOrder = orderByIdOrder;
    }

    @ManyToOne
    @JoinColumn(name = "idResource", referencedColumnName = "id")
    public ResourceEntity getResourceByIdResource() {
        return resourceByIdResource;
    }

    public void setResourceByIdResource(ResourceEntity resourceByIdResource) {
        this.resourceByIdResource = resourceByIdResource;
    }

    @ManyToOne
    @JoinColumn(name = "idSite", referencedColumnName = "id")
    public SiteEntity getSiteByIdSite() {
        return siteByIdSite;
    }

    public void setSiteByIdSite(SiteEntity siteByIdSite) {
        this.siteByIdSite = siteByIdSite;
    }

    @ManyToOne
    @JoinColumn(name = "idGroupResource", referencedColumnName = "id")
    public GroupresourceEntity getGroupresourceByIdGroupResource() {
        return groupresourceByIdGroupResource;
    }

    public void setGroupresourceByIdGroupResource(GroupresourceEntity groupresourceByIdGroupResource) {
        this.groupresourceByIdGroupResource = groupresourceByIdGroupResource;
    }

    @ManyToOne
    @JoinColumn(name = "idTypeResource", referencedColumnName = "id")
    public TyperescourceEntity getTyperescourceByIdTypeResource() {
        return typerescourceByIdTypeResource;
    }

    public void setTyperescourceByIdTypeResource(TyperescourceEntity typerescourceByIdTypeResource) {
        this.typerescourceByIdTypeResource = typerescourceByIdTypeResource;
    }

    @ManyToOne
    @JoinColumn(name = "idProvider", referencedColumnName = "id")
    public ProviderEntity getProviderByIdProvider() {
        return providerByIdProvider;
    }

    public void setProviderByIdProvider(ProviderEntity providerByIdProvider) {
        this.providerByIdProvider = providerByIdProvider;
    }
}
