package com.rengu.entity;

/**每个用户的配置信息
 * Created by wey580231 on 2017/6/22.
 */
public class RG_UserConfigEntity {

    private Integer id;
    private String latestScheduleId;                //最新排程ID信息，在新建排程后，自动更新此值。
    private String currScheduleId;                  //当前对应的排程记录,防止APS调用reply接口多次
    private String rootSnapshotId;                  //当前快照的根节点ID，创建新schedule排程时更新
    private String middleSnapshotId;                //快照树第二层节点ID，创建schedule或紧急排程时更新，用于在接收aps reply后根据ID创建bottom节点
    private String bottomSnapshotId;                //快照树第三层节点ID
    private boolean errorSchedule;                  //是否为紧急排程
    private Integer apsReplyCount;                  //aps在当前MiddleSnapshotId节点下返回结果的次数(区分是否为优化结果)

    private RG_UserConfigEntity user;               //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLatestScheduleId() {
        return latestScheduleId;
    }

    public void setLatestScheduleId(String latestScheduleId) {
        this.latestScheduleId = latestScheduleId;
    }

    public String getCurrScheduleId() {
        return currScheduleId;
    }

    public void setCurrScheduleId(String currScheduleId) {
        this.currScheduleId = currScheduleId;
    }

    public String getRootSnapshotId() {
        return rootSnapshotId;
    }

    public void setRootSnapshotId(String rootSnapshotId) {
        this.rootSnapshotId = rootSnapshotId;
    }

    public String getMiddleSnapshotId() {
        return middleSnapshotId;
    }

    public void setMiddleSnapshotId(String middleSnapshotId) {
        this.middleSnapshotId = middleSnapshotId;
    }

    public String getBottomSnapshotId() {
        return bottomSnapshotId;
    }

    public void setBottomSnapshotId(String bottomSnapshotId) {
        this.bottomSnapshotId = bottomSnapshotId;
    }

    public boolean isErrorSchedule() {
        return errorSchedule;
    }

    public void setErrorSchedule(boolean errorSchedule) {
        this.errorSchedule = errorSchedule;
    }

    public Integer getApsReplyCount() {
        return apsReplyCount;
    }

    public void setApsReplyCount(Integer apsReplyCount) {
        this.apsReplyCount = apsReplyCount;
    }

    public RG_UserConfigEntity getUser() {
        return user;
    }

    public void setUser(RG_UserConfigEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_UserConfigEntity that = (RG_UserConfigEntity) o;

        if (errorSchedule != that.errorSchedule) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (latestScheduleId != null ? !latestScheduleId.equals(that.latestScheduleId) : that.latestScheduleId != null)
            return false;
        if (currScheduleId != null ? !currScheduleId.equals(that.currScheduleId) : that.currScheduleId != null)
            return false;
        if (rootSnapshotId != null ? !rootSnapshotId.equals(that.rootSnapshotId) : that.rootSnapshotId != null)
            return false;
        if (middleSnapshotId != null ? !middleSnapshotId.equals(that.middleSnapshotId) : that.middleSnapshotId != null)
            return false;
        if (bottomSnapshotId != null ? !bottomSnapshotId.equals(that.bottomSnapshotId) : that.bottomSnapshotId != null)
            return false;
        return apsReplyCount != null ? apsReplyCount.equals(that.apsReplyCount) : that.apsReplyCount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (latestScheduleId != null ? latestScheduleId.hashCode() : 0);
        result = 31 * result + (currScheduleId != null ? currScheduleId.hashCode() : 0);
        result = 31 * result + (rootSnapshotId != null ? rootSnapshotId.hashCode() : 0);
        result = 31 * result + (middleSnapshotId != null ? middleSnapshotId.hashCode() : 0);
        result = 31 * result + (bottomSnapshotId != null ? bottomSnapshotId.hashCode() : 0);
        result = 31 * result + (errorSchedule ? 1 : 0);
        result = 31 * result + (apsReplyCount != null ? apsReplyCount.hashCode() : 0);
        return result;
    }
}
