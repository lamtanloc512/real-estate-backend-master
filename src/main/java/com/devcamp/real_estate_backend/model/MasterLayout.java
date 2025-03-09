package com.devcamp.real_estate_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_layout")
public class MasterLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1000, nullable = false)
    private String name;

    @Column(length = 5000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(precision = 12, scale = 2)
    private BigDecimal acreage;

    @Column(name = "apartment_list", length = 1000)
    private String apartmentList;

    @Column(length = 5000)
    private String photo;

    @Column(name = "date_create", updatable = false)
    private LocalDateTime dateCreate;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @PrePersist
    protected void onCreate() {
        dateCreate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdate = LocalDateTime.now();
    }

    // Constructors, Getters, and Setters
    public MasterLayout() {
    }

    public MasterLayout(String name, String description, Project project, BigDecimal acreage, String apartmentList, String photo) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.acreage = acreage;
        this.apartmentList = apartmentList;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public String getApartmentList() {
        return apartmentList;
    }

    public void setApartmentList(String apartmentList) {
        this.apartmentList = apartmentList;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

}
