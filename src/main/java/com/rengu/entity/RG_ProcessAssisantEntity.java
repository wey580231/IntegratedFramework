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

    private String multiNextProcessPlan;            //下一步工序是否存在多种方案，当存在多种方案时，需要根据方案的标识来判断，
    private String mutiPlanFlag;                    //多种方案的标识

    private String autoCreatePreviouseProcess;      //是否需要创建前道工序
    private String previousProcessTasks;            //前道工序
    private String preProcessDistances;             //前道工序的距离集合
    private String preProcessSites;                 //前道工序的地点集合
    private String preProcessMobility;              //前道工序的速度集合
    private String pDelayStartTime;                 //前道工序推迟开始时间
    private String pAdvanceStartTime;               //后道工序提前开始时间

    private String autoCreateNextProcess;           //是否需要紧接着当前工序自动产生吓一条工序，目前只对getRaw和get_product有效
    private String nextProcessTask;                 //工序转换的类型，为getRaw则自动转换movein，为get_product自动转换put_product
    private String nextProcessDistnces;             //运动的距离
    private String nextProcessSites;                //下一步工序的位置集合
    private String nextProcessMobility;             //下一步工序的速度集合
    private String nextProcessRefetTime;            //下一步工序的开始时间是参照当前工序的开始或者结束时间

    private String divisibleSite;                   //以下现在不用，未来与mes调试时会使用。
    private String divisibleTask;

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

    public String getMultiNextProcessPlan() {
        return multiNextProcessPlan;
    }

    public void setMultiNextProcessPlan(String multiNextProcessPlan) {
        this.multiNextProcessPlan = multiNextProcessPlan;
    }

    public String getMutiPlanFlag() {
        return mutiPlanFlag;
    }

    public void setMutiPlanFlag(String mutiPlanFlag) {
        this.mutiPlanFlag = mutiPlanFlag;
    }

    public String getAutoCreatePreviouseProcess() {
        return autoCreatePreviouseProcess;
    }

    public void setAutoCreatePreviouseProcess(String autoCreatePreviouseProcess) {
        this.autoCreatePreviouseProcess = autoCreatePreviouseProcess;
    }

    public String getPreviousProcessTasks() {
        return previousProcessTasks;
    }

    public void setPreviousProcessTasks(String previousProcessTasks) {
        this.previousProcessTasks = previousProcessTasks;
    }

    public String getPreProcessDistances() {
        return preProcessDistances;
    }

    public void setPreProcessDistances(String preProcessDistances) {
        this.preProcessDistances = preProcessDistances;
    }

    public String getPreProcessSites() {
        return preProcessSites;
    }

    public void setPreProcessSites(String preProcessSites) {
        this.preProcessSites = preProcessSites;
    }

    public String getPreProcessMobility() {
        return preProcessMobility;
    }

    public void setPreProcessMobility(String preProcessMobility) {
        this.preProcessMobility = preProcessMobility;
    }

    public String getpDelayStartTime() {
        return pDelayStartTime;
    }

    public void setpDelayStartTime(String pDelayStartTime) {
        this.pDelayStartTime = pDelayStartTime;
    }

    public String getpAdvanceStartTime() {
        return pAdvanceStartTime;
    }

    public void setpAdvanceStartTime(String pAdvanceStartTime) {
        this.pAdvanceStartTime = pAdvanceStartTime;
    }

    public String getAutoCreateNextProcess() {
        return autoCreateNextProcess;
    }

    public void setAutoCreateNextProcess(String autoCreateNextProcess) {
        this.autoCreateNextProcess = autoCreateNextProcess;
    }

    public String getNextProcessTask() {
        return nextProcessTask;
    }

    public void setNextProcessTask(String nextProcessTask) {
        this.nextProcessTask = nextProcessTask;
    }

    public String getNextProcessDistnces() {
        return nextProcessDistnces;
    }

    public void setNextProcessDistnces(String nextProcessDistnces) {
        this.nextProcessDistnces = nextProcessDistnces;
    }

    public String getNextProcessSites() {
        return nextProcessSites;
    }

    public void setNextProcessSites(String nextProcessSites) {
        this.nextProcessSites = nextProcessSites;
    }

    public String getNextProcessMobility() {
        return nextProcessMobility;
    }

    public void setNextProcessMobility(String nextProcessMobility) {
        this.nextProcessMobility = nextProcessMobility;
    }

    public String getNextProcessRefetTime() {
        return nextProcessRefetTime;
    }

    public void setNextProcessRefetTime(String nextProcessRefetTime) {
        this.nextProcessRefetTime = nextProcessRefetTime;
    }

    public String getDivisibleSite() {
        return divisibleSite;
    }

    public void setDivisibleSite(String divisibleSite) {
        this.divisibleSite = divisibleSite;
    }

    public String getDivisibleTask() {
        return divisibleTask;
    }

    public void setDivisibleTask(String divisibleTask) {
        this.divisibleTask = divisibleTask;
    }
}
