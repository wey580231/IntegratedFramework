package com.rengu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wey580231 on 2017/5/23.
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RG_AssisantprocessEntity {
    private String id;
    private Short grp;
    private String typeSite;
    private String idSite;
    private Short minResource;
    private Short maxResource;
    private String siteInGroupResource;
    private Short modResource;
    private String rgPrimary;
    private Short weightParallel;
    private Short weightSequence;
    private RG_ProcessEntity processByIdProcess;
    private Set<RG_TyperescourceEntity> typeresourceById = new HashSet<RG_TyperescourceEntity>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getGrp() {
        return grp;
    }

    public void setGrp(Short grp) {
        this.grp = grp;
    }

    public String getTypeSite() {
        return typeSite;
    }

    public void setTypeSite(String typeSite) {
        this.typeSite = typeSite;
    }

    public String getIdSite() {
        return idSite;
    }

    public void setIdSite(String idSite) {
        this.idSite = idSite;
    }

    public Short getMinResource() {
        return minResource;
    }

    public void setMinResource(Short minResource) {
        this.minResource = minResource;
    }

    public Short getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(Short maxResource) {
        this.maxResource = maxResource;
    }

    public String getSiteInGroupResource() {
        return siteInGroupResource;
    }

    public void setSiteInGroupResource(String siteInGroupResource) {
        this.siteInGroupResource = siteInGroupResource;
    }

    public Short getModResource() {
        return modResource;
    }

    public void setModResource(Short modResource) {
        this.modResource = modResource;
    }

    public String getRgPrimary() {
        return rgPrimary;
    }

    public void setRgPrimary(String rgPrimary) {
        this.rgPrimary = rgPrimary;
    }

    public Short getWeightParallel() {
        return weightParallel;
    }

    public void setWeightParallel(Short weightParallel) {
        this.weightParallel = weightParallel;
    }

    public Short getWeightSequence() {
        return weightSequence;
    }

    public void setWeightSequence(Short weightSequence) {
        this.weightSequence = weightSequence;
    }

    public RG_ProcessEntity getProcessByIdProcess() {
        return processByIdProcess;
    }

    public void setProcessByIdProcess(RG_ProcessEntity processByIdProcess) {
        this.processByIdProcess = processByIdProcess;
    }

    public Set<RG_TyperescourceEntity> getTyperesourceById() {
        return typeresourceById;
    }

    public void setTyperesourceById(Set<RG_TyperescourceEntity> typeresourceById) {
        this.typeresourceById = typeresourceById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RG_AssisantprocessEntity that = (RG_AssisantprocessEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (grp != null ? !grp.equals(that.grp) : that.grp != null) return false;
        if (typeSite != null ? !typeSite.equals(that.typeSite) : that.typeSite != null) return false;
        if (idSite != null ? !idSite.equals(that.idSite) : that.idSite != null) return false;
        if (minResource != null ? !minResource.equals(that.minResource) : that.minResource != null) return false;
        if (maxResource != null ? !maxResource.equals(that.maxResource) : that.maxResource != null) return false;
        if (siteInGroupResource != null ? !siteInGroupResource.equals(that.siteInGroupResource) : that.siteInGroupResource != null)
            return false;
        if (modResource != null ? !modResource.equals(that.modResource) : that.modResource != null) return false;
        if (rgPrimary != null ? !rgPrimary.equals(that.rgPrimary) : that.rgPrimary != null) return false;
        if (weightParallel != null ? !weightParallel.equals(that.weightParallel) : that.weightParallel != null)
            return false;
        if (weightSequence != null ? !weightSequence.equals(that.weightSequence) : that.weightSequence != null)
            return false;
        if (processByIdProcess != null ? !processByIdProcess.equals(that.processByIdProcess) : that.processByIdProcess != null)
            return false;
        return typeresourceById != null ? typeresourceById.equals(that.typeresourceById) : that.typeresourceById == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (grp != null ? grp.hashCode() : 0);
        result = 31 * result + (typeSite != null ? typeSite.hashCode() : 0);
        result = 31 * result + (idSite != null ? idSite.hashCode() : 0);
        result = 31 * result + (minResource != null ? minResource.hashCode() : 0);
        result = 31 * result + (maxResource != null ? maxResource.hashCode() : 0);
        result = 31 * result + (siteInGroupResource != null ? siteInGroupResource.hashCode() : 0);
        result = 31 * result + (modResource != null ? modResource.hashCode() : 0);
        result = 31 * result + (rgPrimary != null ? rgPrimary.hashCode() : 0);
        result = 31 * result + (weightParallel != null ? weightParallel.hashCode() : 0);
        result = 31 * result + (weightSequence != null ? weightSequence.hashCode() : 0);
        result = 31 * result + (processByIdProcess != null ? processByIdProcess.hashCode() : 0);
        result = 31 * result + (typeresourceById != null ? typeresourceById.hashCode() : 0);
        return result;
    }
}
