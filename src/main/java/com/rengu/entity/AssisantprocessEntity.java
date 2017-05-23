package com.rengu.entity;

import javax.persistence.*;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "assisantprocess", schema = "testdatabase", catalog = "")
public class AssisantprocessEntity {
    private String idProcess;
    private String idTypeResource;
    private Short grp;
    private String typeSite;
    private String idSite;
    private Short minResource;
    private Short maxResource;
    private String siteInGroupResource;
    private Short modResource;
    private String primary;
    private Short weightParallel;
    private Short weightSequence;
    private ProcessEntity processByIdProcess;

    @Basic
    @Column(name = "idProcess", nullable = false, length = 255)
    public String getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(String idProcess) {
        this.idProcess = idProcess;
    }

    @Basic
    @Column(name = "IdTypeResource", nullable = true, length = 255)
    public String getIdTypeResource() {
        return idTypeResource;
    }

    public void setIdTypeResource(String idTypeResource) {
        this.idTypeResource = idTypeResource;
    }

    @Basic
    @Column(name = "grp", nullable = true)
    public Short getGrp() {
        return grp;
    }

    public void setGrp(Short grp) {
        this.grp = grp;
    }

    @Basic
    @Column(name = "TypeSite", nullable = true, length = 255)
    public String getTypeSite() {
        return typeSite;
    }

    public void setTypeSite(String typeSite) {
        this.typeSite = typeSite;
    }

    @Basic
    @Column(name = "IdSite", nullable = true, length = 255)
    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    @Basic
    @Column(name = "minResource", nullable = true)
    public Short getMinResource() {
        return minResource;
    }

    public void setMinResource(Short minResource) {
        this.minResource = minResource;
    }

    @Basic
    @Column(name = "maxResource", nullable = true)
    public Short getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(Short maxResource) {
        this.maxResource = maxResource;
    }

    @Basic
    @Column(name = "siteInGroupResource", nullable = true, length = 5)
    public String getSiteInGroupResource() {
        return siteInGroupResource;
    }

    public void setSiteInGroupResource(String siteInGroupResource) {
        this.siteInGroupResource = siteInGroupResource;
    }

    @Basic
    @Column(name = "modResource", nullable = true)
    public Short getModResource() {
        return modResource;
    }

    public void setModResource(Short modResource) {
        this.modResource = modResource;
    }

    @Basic
    @Column(name = "primary", nullable = true, length = 255)
    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    @Basic
    @Column(name = "weightParallel", nullable = true)
    public Short getWeightParallel() {
        return weightParallel;
    }

    public void setWeightParallel(Short weightParallel) {
        this.weightParallel = weightParallel;
    }

    @Basic
    @Column(name = "weightSequence", nullable = true)
    public Short getWeightSequence() {
        return weightSequence;
    }

    public void setWeightSequence(Short weightSequence) {
        this.weightSequence = weightSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssisantprocessEntity that = (AssisantprocessEntity) o;

        if (idProcess != null ? !idProcess.equals(that.idProcess) : that.idProcess != null) return false;
        if (idTypeResource != null ? !idTypeResource.equals(that.idTypeResource) : that.idTypeResource != null)
            return false;
        if (grp != null ? !grp.equals(that.grp) : that.grp != null) return false;
        if (typeSite != null ? !typeSite.equals(that.typeSite) : that.typeSite != null) return false;
        if (idSite != null ? !idSite.equals(that.idSite) : that.idSite != null) return false;
        if (minResource != null ? !minResource.equals(that.minResource) : that.minResource != null) return false;
        if (maxResource != null ? !maxResource.equals(that.maxResource) : that.maxResource != null) return false;
        if (siteInGroupResource != null ? !siteInGroupResource.equals(that.siteInGroupResource) : that.siteInGroupResource != null)
            return false;
        if (modResource != null ? !modResource.equals(that.modResource) : that.modResource != null) return false;
        if (primary != null ? !primary.equals(that.primary) : that.primary != null) return false;
        if (weightParallel != null ? !weightParallel.equals(that.weightParallel) : that.weightParallel != null)
            return false;
        if (weightSequence != null ? !weightSequence.equals(that.weightSequence) : that.weightSequence != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProcess != null ? idProcess.hashCode() : 0;
        result = 31 * result + (idTypeResource != null ? idTypeResource.hashCode() : 0);
        result = 31 * result + (grp != null ? grp.hashCode() : 0);
        result = 31 * result + (typeSite != null ? typeSite.hashCode() : 0);
        result = 31 * result + (idSite != null ? idSite.hashCode() : 0);
        result = 31 * result + (minResource != null ? minResource.hashCode() : 0);
        result = 31 * result + (maxResource != null ? maxResource.hashCode() : 0);
        result = 31 * result + (siteInGroupResource != null ? siteInGroupResource.hashCode() : 0);
        result = 31 * result + (modResource != null ? modResource.hashCode() : 0);
        result = 31 * result + (primary != null ? primary.hashCode() : 0);
        result = 31 * result + (weightParallel != null ? weightParallel.hashCode() : 0);
        result = 31 * result + (weightSequence != null ? weightSequence.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idProcess", referencedColumnName = "id", nullable = false)
    public ProcessEntity getProcessByIdProcess() {
        return processByIdProcess;
    }

    public void setProcessByIdProcess(ProcessEntity processByIdProcess) {
        this.processByIdProcess = processByIdProcess;
    }
}
