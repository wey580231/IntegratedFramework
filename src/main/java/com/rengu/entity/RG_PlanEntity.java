package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by wey580231 on 2017/5/23.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_PlanEntity {
    private String id;
    private String idTask;                                      //工序编码
    private String idJob;                                       //作业编码
    private String nameTask;                                    //工序名称
    private String nameOrder;                                   //订单名称
    private String nameJob;                                     //作业名称
    private String nameResource;                                //资源名称
    private String nameGroupResource;                           //资源所在工组名称
    private String nameTypeResource;                            //资源类型名称
    private String nameSite;                                    //资源所在工位名称
    private String nameProductOrder;                            //产品名称
    private String nameProvider;                                //供应商名称
    private Short ordToParentTask;                              //工序号
    private String idTaskResourceSucc;                          //下道工序编码
    private String preemptiveTask;                              //是否可抢占，1：可抢占；0：不可抢占
    private String divisibleTask;                               //工序是否可拆分到多个资源上加工
    private String continuousTask;                              //工序是否连续加工
    private Short quantityTask;                                 //加工总数量
    private Short quantityResourceTask;                         //工序在资源上加工的数量
    private Short quantityBatchTask;                            //工序在资源上批次加工的数量
    private Short qtySequence;                                  //序列内消耗型资源已消耗数量
    private String t1Task;                                      //工序开始时间
    private String t2Task;                                      //工序占用资源的结束时间
    private String t2ExtendedTask;                              //工序实际结束时间
    private String advice;                                      //拖期改进建议
    private String estimateTask;                                //估时
    private String timeTask;                                    //工序实际加工时间
    private String t10Task;                                     //工序预固定开工时间
    private String t20Task;                                     //工序预固定完工时间
    private String t20ExtendedTask;                             //工序预固定实际完工时间
    private String t1Job;                                       //作业开始时间
    private String t2Job;                                       //作业结束时间
    private String initTimeTask;                                //工序准备时间
    private String unitTimeTask;                                //工序单件时间
    private String postTimeTask;                                //冷却时间
    private String checkTimeTask;                               //检验时间
    private String idGroupResource0Task;                        //工序预固定工组编码
    private String idResource0Task;                             //工序预固定资源编码
    private String idSite0Task;                                 //工序预固定工位编码
    private Short quantity0Task;                                //工序预固定加工数量
    private Short quantityJob;                                  //作业数量
    private Short nbTaskJob;                                    //作业内工序数
    private String refProductJob;                               //作业图纸号
    private Short ordToRootJob;                                 //作业号
    private String ordToRootChildJob;                           //下一级作业号
    private String t1Order;                                     //订单最早开工时间
    private String t2Order;                                     //订单最晚交付时间
    private Short quantityOrder;                                //订单数量
    private Short priorityOrder;                                //订单优先级
    private String colorOrder;                                  //订单在View中显示的颜色
    private Byte state;                                         //状态

    private RG_ClubEntity clubByIdClub;
    private RG_ProcessEntity processByIdProcess;
    private RG_OrderEntity orderByIdOrder;
    private RG_ResourceEntity resourceByIdResource;
    private RG_SiteEntity siteByIdSite;
    private RG_GroupresourceEntity groupresourceByIdGroupResource;
    private RG_TyperescourceEntity typerescourceByIdTypeResource;
    private RG_ProviderEntity providerByIdProvider;
    private RG_ProductEntity productByIdProduct;

    private RG_SnapshotNodeEntity snapShort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public String getNameGroupResource() {
        return nameGroupResource;
    }

    public void setNameGroupResource(String nameGroupResource) {
        this.nameGroupResource = nameGroupResource;
    }

    public String getNameTypeResource() {
        return nameTypeResource;
    }

    public void setNameTypeResource(String nameTypeResource) {
        this.nameTypeResource = nameTypeResource;
    }

    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    public String getNameProductOrder() {
        return nameProductOrder;
    }

    public void setNameProductOrder(String nameProductOrder) {
        this.nameProductOrder = nameProductOrder;
    }

    public String getNameProvider() {
        return nameProvider;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    public Short getOrdToParentTask() {
        return ordToParentTask;
    }

    public void setOrdToParentTask(Short ordToParentTask) {
        this.ordToParentTask = ordToParentTask;
    }

    public String getIdTaskResourceSucc() {
        return idTaskResourceSucc;
    }

    public void setIdTaskResourceSucc(String idTaskResourceSucc) {
        this.idTaskResourceSucc = idTaskResourceSucc;
    }

    public String getPreemptiveTask() {
        return preemptiveTask;
    }

    public void setPreemptiveTask(String preemptiveTask) {
        this.preemptiveTask = preemptiveTask;
    }

    public String getDivisibleTask() {
        return divisibleTask;
    }

    public void setDivisibleTask(String divisibleTask) {
        this.divisibleTask = divisibleTask;
    }

    public String getContinuousTask() {
        return continuousTask;
    }

    public void setContinuousTask(String continuousTask) {
        this.continuousTask = continuousTask;
    }

    public Short getQuantityTask() {
        return quantityTask;
    }

    public void setQuantityTask(Short quantityTask) {
        this.quantityTask = quantityTask;
    }

    public Short getQuantityResourceTask() {
        return quantityResourceTask;
    }

    public void setQuantityResourceTask(Short quantityResourceTask) {
        this.quantityResourceTask = quantityResourceTask;
    }

    public Short getQuantityBatchTask() {
        return quantityBatchTask;
    }

    public void setQuantityBatchTask(Short quantityBatchTask) {
        this.quantityBatchTask = quantityBatchTask;
    }

    public Short getQtySequence() {
        return qtySequence;
    }

    public void setQtySequence(Short qtySequence) {
        this.qtySequence = qtySequence;
    }

    public String getT1Task() {
        return t1Task;
    }

    public void setT1Task(String t1Task) {
        this.t1Task = t1Task;
    }

    public String getT2Task() {
        return t2Task;
    }

    public void setT2Task(String t2Task) {
        this.t2Task = t2Task;
    }

    public String getT2ExtendedTask() {
        return t2ExtendedTask;
    }

    public void setT2ExtendedTask(String t2ExtendedTask) {
        this.t2ExtendedTask = t2ExtendedTask;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getEstimateTask() {
        return estimateTask;
    }

    public void setEstimateTask(String estimateTask) {
        this.estimateTask = estimateTask;
    }

    public String getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(String timeTask) {
        this.timeTask = timeTask;
    }

    public String getT10Task() {
        return t10Task;
    }

    public void setT10Task(String t10Task) {
        this.t10Task = t10Task;
    }

    public String getT20Task() {
        return t20Task;
    }

    public void setT20Task(String t20Task) {
        this.t20Task = t20Task;
    }

    public String getT20ExtendedTask() {
        return t20ExtendedTask;
    }

    public void setT20ExtendedTask(String t20ExtendedTask) {
        this.t20ExtendedTask = t20ExtendedTask;
    }

    public String getT1Job() {
        return t1Job;
    }

    public void setT1Job(String t1Job) {
        this.t1Job = t1Job;
    }

    public String getT2Job() {
        return t2Job;
    }

    public void setT2Job(String t2Job) {
        this.t2Job = t2Job;
    }

    public String getInitTimeTask() {
        return initTimeTask;
    }

    public void setInitTimeTask(String initTimeTask) {
        this.initTimeTask = initTimeTask;
    }

    public String getUnitTimeTask() {
        return unitTimeTask;
    }

    public void setUnitTimeTask(String unitTimeTask) {
        this.unitTimeTask = unitTimeTask;
    }

    public String getPostTimeTask() {
        return postTimeTask;
    }

    public void setPostTimeTask(String postTimeTask) {
        this.postTimeTask = postTimeTask;
    }

    public String getCheckTimeTask() {
        return checkTimeTask;
    }

    public void setCheckTimeTask(String checkTimeTask) {
        this.checkTimeTask = checkTimeTask;
    }

    public String getIdGroupResource0Task() {
        return idGroupResource0Task;
    }

    public void setIdGroupResource0Task(String idGroupResource0Task) {
        this.idGroupResource0Task = idGroupResource0Task;
    }

    public String getIdResource0Task() {
        return idResource0Task;
    }

    public void setIdResource0Task(String idResource0Task) {
        this.idResource0Task = idResource0Task;
    }

    public String getIdSite0Task() {
        return idSite0Task;
    }

    public void setIdSite0Task(String idSite0Task) {
        this.idSite0Task = idSite0Task;
    }

    public Short getQuantity0Task() {
        return quantity0Task;
    }

    public void setQuantity0Task(Short quantity0Task) {
        this.quantity0Task = quantity0Task;
    }

    public Short getQuantityJob() {
        return quantityJob;
    }

    public void setQuantityJob(Short quantityJob) {
        this.quantityJob = quantityJob;
    }

    public Short getNbTaskJob() {
        return nbTaskJob;
    }

    public void setNbTaskJob(Short nbTaskJob) {
        this.nbTaskJob = nbTaskJob;
    }

    public String getRefProductJob() {
        return refProductJob;
    }

    public void setRefProductJob(String refProductJob) {
        this.refProductJob = refProductJob;
    }

    public Short getOrdToRootJob() {
        return ordToRootJob;
    }

    public void setOrdToRootJob(Short ordToRootJob) {
        this.ordToRootJob = ordToRootJob;
    }

    public String getOrdToRootChildJob() {
        return ordToRootChildJob;
    }

    public void setOrdToRootChildJob(String ordToRootChildJob) {
        this.ordToRootChildJob = ordToRootChildJob;
    }

    public String getT1Order() {
        return t1Order;
    }

    public void setT1Order(String t1Order) {
        this.t1Order = t1Order;
    }

    public String getT2Order() {
        return t2Order;
    }

    public void setT2Order(String t2Order) {
        this.t2Order = t2Order;
    }

    public Short getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Short quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Short getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(Short priorityOrder) {
        this.priorityOrder = priorityOrder;
    }

    public String getColorOrder() {
        return colorOrder;
    }

    public void setColorOrder(String colorOrder) {
        this.colorOrder = colorOrder;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public RG_ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(RG_ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }

    public RG_ProcessEntity getProcessByIdProcess() {
        return processByIdProcess;
    }

    public void setProcessByIdProcess(RG_ProcessEntity processByIdProcess) {
        this.processByIdProcess = processByIdProcess;
    }

    public RG_OrderEntity getOrderByIdOrder() {
        return orderByIdOrder;
    }

    public void setOrderByIdOrder(RG_OrderEntity orderByIdOrder) {
        this.orderByIdOrder = orderByIdOrder;
    }

    public RG_ResourceEntity getResourceByIdResource() {
        return resourceByIdResource;
    }

    public void setResourceByIdResource(RG_ResourceEntity resourceByIdResource) {
        this.resourceByIdResource = resourceByIdResource;
    }

    public RG_SiteEntity getSiteByIdSite() {
        return siteByIdSite;
    }

    public void setSiteByIdSite(RG_SiteEntity siteByIdSite) {
        this.siteByIdSite = siteByIdSite;
    }

    public RG_GroupresourceEntity getGroupresourceByIdGroupResource() {
        return groupresourceByIdGroupResource;
    }

    public void setGroupresourceByIdGroupResource(RG_GroupresourceEntity groupresourceByIdGroupResource) {
        this.groupresourceByIdGroupResource = groupresourceByIdGroupResource;
    }

    public RG_TyperescourceEntity getTyperescourceByIdTypeResource() {
        return typerescourceByIdTypeResource;
    }

    public void setTyperescourceByIdTypeResource(RG_TyperescourceEntity typerescourceByIdTypeResource) {
        this.typerescourceByIdTypeResource = typerescourceByIdTypeResource;
    }

    public RG_ProviderEntity getProviderByIdProvider() {
        return providerByIdProvider;
    }

    public void setProviderByIdProvider(RG_ProviderEntity providerByIdProvider) {
        this.providerByIdProvider = providerByIdProvider;
    }

    public RG_ProductEntity getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(RG_ProductEntity productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }

    public RG_SnapshotNodeEntity getSnapShort() {
        return snapShort;
    }

    public void setSnapShort(RG_SnapshotNodeEntity snapShort) {
        this.snapShort = snapShort;
    }


}
