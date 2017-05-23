package com.rengu.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "resource", schema = "testdatabase", catalog = "")
public class ResourceEntity {
    private String id;
    private String name;
    private String idTypeResource;
    private String idFeatureResource;
    private String idSite;
    private String idSiteGroupResource;
    private String typeSite;
    private Short mobility;
    private String sameTypeSequence;
    private String idSiteSequence;
    private Short quantity0;
    private String critical;
    private String idShift;
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
    private Collection<PlanEntity> plansById;
    private GroupresourceEntity groupresourceByIdGroupResource;
    private UserEntity userByIdUser;
    private ClubEntity clubByIdClub;

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
    @Column(name = "IdTypeResource")
    public String getIdTypeResource() {
        return idTypeResource;
    }

    public void setIdTypeResource(String idTypeResource) {
        this.idTypeResource = idTypeResource;
    }

    @Basic
    @Column(name = "idFeatureResource")
    public String getIdFeatureResource() {
        return idFeatureResource;
    }

    public void setIdFeatureResource(String idFeatureResource) {
        this.idFeatureResource = idFeatureResource;
    }

    @Basic
    @Column(name = "IdSite")
    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    @Basic
    @Column(name = "IdSiteGroupResource")
    public String getIdSiteGroupResource() {
        return idSiteGroupResource;
    }

    public void setIdSiteGroupResource(String idSiteGroupResource) {
        this.idSiteGroupResource = idSiteGroupResource;
    }

    @Basic
    @Column(name = "TypeSite")
    public String getTypeSite() {
        return typeSite;
    }

    public void setTypeSite(String typeSite) {
        this.typeSite = typeSite;
    }

    @Basic
    @Column(name = "mobility")
    public Short getMobility() {
        return mobility;
    }

    public void setMobility(Short mobility) {
        this.mobility = mobility;
    }

    @Basic
    @Column(name = "sameTypeSequence")
    public String getSameTypeSequence() {
        return sameTypeSequence;
    }

    public void setSameTypeSequence(String sameTypeSequence) {
        this.sameTypeSequence = sameTypeSequence;
    }

    @Basic
    @Column(name = "idSiteSequence")
    public String getIdSiteSequence() {
        return idSiteSequence;
    }

    public void setIdSiteSequence(String idSiteSequence) {
        this.idSiteSequence = idSiteSequence;
    }

    @Basic
    @Column(name = "quantity0")
    public Short getQuantity0() {
        return quantity0;
    }

    public void setQuantity0(Short quantity0) {
        this.quantity0 = quantity0;
    }

    @Basic
    @Column(name = "critical")
    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    @Basic
    @Column(name = "IdShift")
    public String getIdShift() {
        return idShift;
    }

    public void setIdShift(String idShift) {
        this.idShift = idShift;
    }

    @Basic
    @Column(name = "NameShift")
    public String getNameShift() {
        return nameShift;
    }

    public void setNameShift(String nameShift) {
        this.nameShift = nameShift;
    }

    @Basic
    @Column(name = "Calendar")
    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    @Basic
    @Column(name = "Slot")
    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    @Basic
    @Column(name = "DateForbidden")
    public String getDateForbidden() {
        return dateForbidden;
    }

    public void setDateForbidden(String dateForbidden) {
        this.dateForbidden = dateForbidden;
    }

    @Basic
    @Column(name = "weekend")
    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "makespan")
    public String getMakespan() {
        return makespan;
    }

    public void setMakespan(String makespan) {
        this.makespan = makespan;
    }

    @Basic
    @Column(name = "rate")
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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
    @Column(name = "sizeIcon")
    public Byte getSizeIcon() {
        return sizeIcon;
    }

    public void setSizeIcon(Byte sizeIcon) {
        this.sizeIcon = sizeIcon;
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
    @Column(name = "idSite0")
    public String getIdSite0() {
        return idSite0;
    }

    public void setIdSite0(String idSite0) {
        this.idSite0 = idSite0;
    }

    @Basic
    @Column(name = "unit")
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

        ResourceEntity that = (ResourceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idTypeResource != null ? !idTypeResource.equals(that.idTypeResource) : that.idTypeResource != null)
            return false;
        if (idFeatureResource != null ? !idFeatureResource.equals(that.idFeatureResource) : that.idFeatureResource != null)
            return false;
        if (idSite != null ? !idSite.equals(that.idSite) : that.idSite != null) return false;
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
        if (idShift != null ? !idShift.equals(that.idShift) : that.idShift != null) return false;
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
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idTypeResource != null ? idTypeResource.hashCode() : 0);
        result = 31 * result + (idFeatureResource != null ? idFeatureResource.hashCode() : 0);
        result = 31 * result + (idSite != null ? idSite.hashCode() : 0);
        result = 31 * result + (idSiteGroupResource != null ? idSiteGroupResource.hashCode() : 0);
        result = 31 * result + (typeSite != null ? typeSite.hashCode() : 0);
        result = 31 * result + (mobility != null ? mobility.hashCode() : 0);
        result = 31 * result + (sameTypeSequence != null ? sameTypeSequence.hashCode() : 0);
        result = 31 * result + (idSiteSequence != null ? idSiteSequence.hashCode() : 0);
        result = 31 * result + (quantity0 != null ? quantity0.hashCode() : 0);
        result = 31 * result + (critical != null ? critical.hashCode() : 0);
        result = 31 * result + (idShift != null ? idShift.hashCode() : 0);
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

    @OneToMany(mappedBy = "resourceByIdResource")
    public Collection<PlanEntity> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<PlanEntity> plansById) {
        this.plansById = plansById;
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
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    public UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "idClub", referencedColumnName = "id")
    public ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }
}
