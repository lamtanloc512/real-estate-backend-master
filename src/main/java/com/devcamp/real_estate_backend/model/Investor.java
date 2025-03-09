package com.devcamp.real_estate_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "investor")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1000, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 2000)
    private String address;

    @Column(length = 2000)
    private String projects;

    @Column(length = 50)
    private String phone;

    @Column(length = 50)
    private String phone2;

    @Column(length = 50)
    private String fax;

    @Column(length = 200)
    private String email;

    @Column(length = 1000)
    private String website;

    @Column(columnDefinition = "TEXT")
    private String note;
    
    public Investor(Integer id, String name, String description, String projects, String address, String phone,
            String phone2, String fax, String email, String website, String note) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projects = projects;
        this.address = address;
        this.phone = phone;
        this.phone2 = phone2;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.note = note;
    }

    public Investor() {
    }

    // Getters and Setters
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

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
