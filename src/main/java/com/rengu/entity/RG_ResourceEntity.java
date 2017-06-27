package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

/**
 * Created by wey580231 on 2017/5/23.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_ResourceEntity {
    private String id;
    private String name;
    private String idFeatureResource;
    private String idSiteGroupResource;
    private String typeSite;
    private Short mobility;
    private String sameTypeSequence;
    private String idSiteSequence;
    private Short quantity0;
    private String critical;
    private String nameShift;
    private String calendar;
    private String slot;
    private String dateForbidden;
    private String weekend;
    private String color;
    private String makespan;
    private Double rate;
    private Byte state;
    private Byte sizeIcon;
    private String idIcon;
    private String idSite0;
    private String unit;

    private RG_ClubEntity clubByIdClub;
    private RG_UserEntity userByIdUser;
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    private RG_GroupresourceEntity groupresourceByIdGroupResource;
    private Set<RG_SiteEntity> sitesById;
    private Set<RG_ShiftEntity> shiftsById;
    private Set<RG_TyperescourceEntity> typeresourcesById;
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ScheduleId")
    private Set<RG_ScheduleEntity> schedules;

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

    public String getIdFeatureResource() {
        return idFeatureResource;
    }

    public void setIdFeatureResource(String idFeatureResource) {
        this.idFeatureResource = idFeatureResource;
    }

    public String getIdSiteGroupResource() {
        return idSiteGroupResource;
    }

    public void setIdSiteGroupResource(String idSiteGroupResource) {
        this.idSiteGroupResource = idSiteGroupResource;
    }

    public String getTypeSite() {
        return typeSite;
    }

    public void setTypeSite(String typeSite) {
        this.typeSite = typeSite;
    }

    public Short getMobility() {
        return mobility;
    }

    public void setMobility(Short mobility) {
        this.mobility = mobility;
    }

    public String getSameTypeSequence() {
        return sameTypeSequence;
    }

    public void setSameTypeSequence(String sameTypeSequence) {
        this.sameTypeSequence = sameTypeSequence;
    }

    public String getIdSiteSequence() {
        return idSiteSequence;
    }

    public void setIdSiteSequence(String idSiteSequence) {
        this.idSiteSequence = idSiteSequence;
    }

    public Short getQuantity0() {
        return quantity0;
    }

    public void setQuantity0(Short quantity0) {
        this.quantity0 = quantity0;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getNameShift() {
        return nameShift;
    }

    public void setNameShift(String nameShift) {
        this.nameShift = nameShift;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getDateForbidden() {
        return dateForbidden;
    }

    public void setDateForbidden(String dateForbidden) {
        this.dateForbidden = dateForbidden;
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMakespan() {
        return makespan;
    }

    public void setMakespan(String makespan) {
        this.makespan = makespan;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getSizeIcon() {
        return sizeIcon;
    }

    public void setSizeIcon(Byte sizeIcon) {
        this.sizeIcon = sizeIcon;
    }

    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    public String getIdSite0() {
        return idSite0;
    }

    public void setIdSite0(String idSite0) {
        this.idSite0 = idSite0;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_ResourceEntity that = (RG_ResourceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idFeatureResource != null ? !idFeatureResource.equals(that.idFeatureResource) : that.idFeatureResource != null)
            return false;
        if (idSiteGroupResource != null ? !idSiteGroupResource.equals(that.idSiteGroupResource) : that.idSiteGroupResource != null)
            return false;
        if (typeSite != null ? !typeSite.equals(that.typeSite) : that.typeSite != null) return false;
        if (mobility != null ? !mobility.equals(that.mobility) : that.mobility != null) return false;
        if (sameTypeSequence != null ? !sameTypeSequence.equals(that.sameTypeSequence) : that.sameTypeSequence != null)
            return false;
        if (idSiteSequence != null ? !idSiteSequence.equals(that.idSiteSequence) : that.idSiteSequence != null)
            return false;
        if (quantity0 != null ? !quantity0.equals(that.quantity0) : that.quantity0 != null) return false;
        if (critical != null ? !critical.equals(that.critical) : that.critical != null) return false;
        if (nameShift != null ? !nameShift.equals(that.nameShift) : that.nameShift != null) return false;
        if (calendar != null ? !calendar.equals(that.calendar) : that.calendar != null) return false;
        if (slot != null ? !slot.equals(that.slot) : that.slot != null) return false;
        if (dateForbidden != null ? !dateForbidden.equals(that.dateForbidden) : that.dateForbidden != null)
            return false;
        if (weekend != null ? !weekend.equals(that.weekend) : that.weekend != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (makespan != null ? !makespan.equals(that.makespan) : that.makespan != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (sizeIcon != null ? !sizeIcon.equals(that.sizeIcon) : that.sizeIcon != null) return false;
        if (idIcon != null ? !idIcon.equals(that.idIcon) : that.idIcon != null) return false;
        if (idSite0 != null ? !idSite0.equals(that.idSite0) : that.idSite0 != null) return false;
        return unit != null ? unit.equals(that.unit) : that.unit == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idFeatureResource != null ? idFeatureResource.hashCode() : 0);
        result = 31 * result + (idSiteGroupResource != null ? idSiteGroupResource.hashCode() : 0);
        result = 31 * result + (typeSite != null ? typeSite.hashCode() : 0);
        result = 31 * result + (mobility != null ? mobility.hashCode() : 0);
        result = 31 * result + (sameTypeSequence != null ? sameTypeSequence.hashCode() : 0);
        result = 31 * result + (idSiteSequence != null ? idSiteSequence.hashCode() : 0);
        result = 31 * result + (quantity0 != null ? quantity0.hashCode() : 0);
        result = 31 * result + (critical != null ? critical.hashCode() : 0);
        result = 31 * result + (nameShift != null ? nameShift.hashCode() : 0);
        result = 31 * result + (calendar != null ? calendar.hashCode() : 0);
        result = 31 * result + (slot != null ? slot.hashCode() : 0);
        result = 31 * result + (dateForbidden != null ? dateForbidden.hashCode() : 0);
        result = 31 * result + (weekend != null ? weekend.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (makespan != null ? makespan.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (sizeIcon != null ? sizeIcon.hashCode() : 0);
        result = 31 * result + (idIcon != null ? idIcon.hashCode() : 0);
        result = 31 * result + (idSite0 != null ? idSite0.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    public RG_GroupresourceEntity getGroupresourceByIdGroupResource() {
        return groupresourceByIdGroupResource;
    }

    public void setGroupresourceByIdGroupResource(RG_GroupresourceEntity groupresourceByIdGroupResource) {
        this.groupresourceByIdGroupResource = groupresourceByIdGroupResource;
    }

    public RG_UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(RG_UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    public RG_ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(RG_ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }

    public Set<RG_SiteEntity> getSitesById() {
        return sitesById;
    }

    public void setSitesById(Set<RG_SiteEntity> sitesById) {
        this.sitesById = sitesById;
    }

    public Set<RG_ShiftEntity> getShiftsById() {
        return shiftsById;
    }

    public void setShiftsById(Set<RG_ShiftEntity> shiftsById) {
        this.shiftsById = shiftsById;
    }

    public Set<RG_TyperescourceEntity> getTyperesourcesById() {
        return typeresourcesById;
    }

    public void setTyperesourcesById(Set<RG_TyperescourceEntity> typeresourcesById) {
        this.typeresourcesById = typeresourcesById;
    }

    public Set<RG_ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<RG_ScheduleEntity> schedules) {
        this.schedules = schedules;
    }

}
