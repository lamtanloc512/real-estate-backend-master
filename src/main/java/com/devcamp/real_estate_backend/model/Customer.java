package com.devcamp.real_estate_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contact_name", length = 255)
    private String contactName;

    @Column(name = "contact_title", length = 255)
    private String contactTitle;

    @Column(length = 255)
    private String address;

    @Column(length = 80)
    private String mobile;

    @Column(length = 255)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "create_by")
    private Integer create_by;

    @Column(name = "update_by")
    private Integer update_by;

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }

    public Customer() {
    }

    public Customer(Integer id, String contactName, String contactTitle, String address, String mobile, String email,
            String note, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.note = note;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    
}
