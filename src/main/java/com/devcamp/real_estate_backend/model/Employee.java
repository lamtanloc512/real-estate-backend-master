package com.devcamp.real_estate_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.devcamp.real_estate_backend.auth.entity.Role;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "employeeid", unique = true, nullable = false)
    private String employeeId;

    @Column(length = 20, nullable = false)
    private String lastName;

    @Column(length = 10, nullable = false)
    private String firstName;

    @Column(length = 30)
    private String title;

    @Column(length = 25)
    private String titleOfCourtesy;

    @Column
    private LocalDateTime birthDate;

    @Column
    private LocalDateTime hireDate;

    @Column(length = 60)
    private String address;

    @Column(length = 15)
    private String city;

    @Column(length = 15)
    private String region;

    @Column(length = 10)
    private String postalCode;

    @Column(length = 15)
    private String country;

    @Column(length = 24)
    private String homePhone;

    @Column(length = 4)
    private String extension;

    @Column(length = 255)
    private String photo;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "ReportsTo")
    private Employee reportsTo;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(length = 255)
    private String email;

    @Column(columnDefinition = "ENUM('Y','N')")
    private String activated;

    @Column(columnDefinition = "TEXT")
    private String profile;

    @Column(name = "user_level", nullable = false)
    private Integer userLevel;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Constructors, Getters, and Setters
    public Employee() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Employee(String lastName, String firstName, String title, String username, String password, String email, Integer userLevel) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userLevel = userLevel;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Employee reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

}
