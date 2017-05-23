package com.rengu.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by wey580231 on 2017/5/23.
 */
@Entity
@Table(name = "order", schema = "testdatabase", catalog = "")
public class OrderEntity {
    private String id;
    private String name;
    private String origin;
    private String type;
    private String idClient;
    private String idProvider;
    private String idGroupResource;
    private Short quantity;
    private Short priority;
    private String t0;
    private String t1;
    private String t2;
    private Short ord;
    private String idPree;
    private String idSucc;
    private String idExclusive;
    private String t1Interaction;
    private String t2Interaction;
    private String t1Plan;
    private String t2Plan;
    private Short estimate;
    private Short advance;
    private Short delay;
    private String color;
    private Byte state;
    private Byte selected;
    private Short nbTask;
    private ProductEntity productByIdProduct;
    private ClubEntity clubByIdClub;
    private Collection<PlanEntity> plansById;

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
    @Column(name = "origin")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "idClient")
    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "IdProvider")
    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    @Basic
    @Column(name = "IdGroupResource")
    public String getIdGroupResource() {
        return idGroupResource;
    }

    public void setIdGroupResource(String idGroupResource) {
        this.idGroupResource = idGroupResource;
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
    @Column(name = "priority")
    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "t0")
    public String getT0() {
        return t0;
    }

    public void setT0(String t0) {
        this.t0 = t0;
    }

    @Basic
    @Column(name = "t1")
    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    @Basic
    @Column(name = "t2")
    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    @Basic
    @Column(name = "ord")
    public Short getOrd() {
        return ord;
    }

    public void setOrd(Short ord) {
        this.ord = ord;
    }

    @Basic
    @Column(name = "IdPree")
    public String getIdPree() {
        return idPree;
    }

    public void setIdPree(String idPree) {
        this.idPree = idPree;
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
    @Column(name = "IdExclusive")
    public String getIdExclusive() {
        return idExclusive;
    }

    public void setIdExclusive(String idExclusive) {
        this.idExclusive = idExclusive;
    }

    @Basic
    @Column(name = "t1Interaction")
    public String getT1Interaction() {
        return t1Interaction;
    }

    public void setT1Interaction(String t1Interaction) {
        this.t1Interaction = t1Interaction;
    }

    @Basic
    @Column(name = "t2Interaction")
    public String getT2Interaction() {
        return t2Interaction;
    }

    public void setT2Interaction(String t2Interaction) {
        this.t2Interaction = t2Interaction;
    }

    @Basic
    @Column(name = "t1Plan")
    public String getT1Plan() {
        return t1Plan;
    }

    public void setT1Plan(String t1Plan) {
        this.t1Plan = t1Plan;
    }

    @Basic
    @Column(name = "t2Plan")
    public String getT2Plan() {
        return t2Plan;
    }

    public void setT2Plan(String t2Plan) {
        this.t2Plan = t2Plan;
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
    @Column(name = "advance")
    public Short getAdvance() {
        return advance;
    }

    public void setAdvance(Short advance) {
        this.advance = advance;
    }

    @Basic
    @Column(name = "delay")
    public Short getDelay() {
        return delay;
    }

    public void setDelay(Short delay) {
        this.delay = delay;
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
    @Column(name = "nbTask")
    public Short getNbTask() {
        return nbTask;
    }

    public void setNbTask(Short nbTask) {
        this.nbTask = nbTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (idClient != null ? !idClient.equals(that.idClient) : that.idClient != null) return false;
        if (idProvider != null ? !idProvider.equals(that.idProvider) : that.idProvider != null) return false;
        if (idGroupResource != null ? !idGroupResource.equals(that.idGroupResource) : that.idGroupResource != null)
            return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (t0 != null ? !t0.equals(that.t0) : that.t0 != null) return false;
        if (t1 != null ? !t1.equals(that.t1) : that.t1 != null) return false;
        if (t2 != null ? !t2.equals(that.t2) : that.t2 != null) return false;
        if (ord != null ? !ord.equals(that.ord) : that.ord != null) return false;
        if (idPree != null ? !idPree.equals(that.idPree) : that.idPree != null) return false;
        if (idSucc != null ? !idSucc.equals(that.idSucc) : that.idSucc != null) return false;
        if (idExclusive != null ? !idExclusive.equals(that.idExclusive) : that.idExclusive != null) return false;
        if (t1Interaction != null ? !t1Interaction.equals(that.t1Interaction) : that.t1Interaction != null)
            return false;
        if (t2Interaction != null ? !t2Interaction.equals(that.t2Interaction) : that.t2Interaction != null)
            return false;
        if (t1Plan != null ? !t1Plan.equals(that.t1Plan) : that.t1Plan != null) return false;
        if (t2Plan != null ? !t2Plan.equals(that.t2Plan) : that.t2Plan != null) return false;
        if (estimate != null ? !estimate.equals(that.estimate) : that.estimate != null) return false;
        if (advance != null ? !advance.equals(that.advance) : that.advance != null) return false;
        if (delay != null ? !delay.equals(that.delay) : that.delay != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (selected != null ? !selected.equals(that.selected) : that.selected != null) return false;
        if (nbTask != null ? !nbTask.equals(that.nbTask) : that.nbTask != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (idProvider != null ? idProvider.hashCode() : 0);
        result = 31 * result + (idGroupResource != null ? idGroupResource.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (t0 != null ? t0.hashCode() : 0);
        result = 31 * result + (t1 != null ? t1.hashCode() : 0);
        result = 31 * result + (t2 != null ? t2.hashCode() : 0);
        result = 31 * result + (ord != null ? ord.hashCode() : 0);
        result = 31 * result + (idPree != null ? idPree.hashCode() : 0);
        result = 31 * result + (idSucc != null ? idSucc.hashCode() : 0);
        result = 31 * result + (idExclusive != null ? idExclusive.hashCode() : 0);
        result = 31 * result + (t1Interaction != null ? t1Interaction.hashCode() : 0);
        result = 31 * result + (t2Interaction != null ? t2Interaction.hashCode() : 0);
        result = 31 * result + (t1Plan != null ? t1Plan.hashCode() : 0);
        result = 31 * result + (t2Plan != null ? t2Plan.hashCode() : 0);
        result = 31 * result + (estimate != null ? estimate.hashCode() : 0);
        result = 31 * result + (advance != null ? advance.hashCode() : 0);
        result = 31 * result + (delay != null ? delay.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (selected != null ? selected.hashCode() : 0);
        result = 31 * result + (nbTask != null ? nbTask.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    public ProductEntity getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(ProductEntity productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }

    @ManyToOne
    @JoinColumn(name = "idClub", referencedColumnName = "id")
    public ClubEntity getClubByIdClub() {
        return clubByIdClub;
    }

    public void setClubByIdClub(ClubEntity clubByIdClub) {
        this.clubByIdClub = clubByIdClub;
    }

    @OneToMany(mappedBy = "orderByIdOrder")
    public Collection<PlanEntity> getPlansById() {
        return plansById;
    }

    public void setPlansById(Collection<PlanEntity> plansById) {
        this.plansById = plansById;
    }
}
