package com.devcamp.real_estate_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String name;

    @ManyToOne
    @JoinColumn(name = "_province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "_district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "_ward_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "_street_id")
    private Street street;

    @Column(length = 1000)
    private String address;

    @Column(precision = 20, scale = 2)
    private BigDecimal acreage;

    @ManyToOne
    @JoinColumn(name = "investor")
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "construction_contractor")
    private ConstructionContractor constructionContractor;

    @ManyToOne
    @JoinColumn(name = "design_unit")
    private DesignUnit designUnit;

    @Column(columnDefinition = "TEXT")
    private String slogan;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "construct_area", precision = 20, scale = 2)
    private BigDecimal constructArea;

    @Column(name = "num_block")
    private Short numBlock;

    @Column(name = "num_floors", length = 500)
    private String numFloors;

    @Column(name = "num_apartment")
    private Integer numApartment;

    @Column(name = "apartmentt_area", length = 500)
    private String apartmentArea;

    @Column(name = "utilities", length = 1000)
    private String utilities;

    @Column(name = "region_link", length = 1000)
    private String regionLink;

    @Column(name = "photo", length = 5000)
    private String photo;

    @Column(name = "_lat", precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "_lng", precision = 10, scale = 6)
    private BigDecimal longitude;

    public Project() {
    }

    public Project(Integer id, String name, Province province, District district, Ward ward, Street street,
            String address, BigDecimal acreage, Investor investor, ConstructionContractor constructionContractor,
            DesignUnit designUnit, String slogan, String description, BigDecimal constructArea, Short numBlock,
            String numFloors, Integer numApartment, String apartmentArea, String utilities, String regionLink,
            String photo, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.street = street;
        this.address = address;
        this.acreage = acreage;
        this.investor = investor;
        this.constructionContractor = constructionContractor;
        this.designUnit = designUnit;
        this.slogan = slogan;
        this.description = description;
        this.constructArea = constructArea;
        this.numBlock = numBlock;
        this.numFloors = numFloors;
        this.numApartment = numApartment;
        this.apartmentArea = apartmentArea;
        this.utilities = utilities;
        this.regionLink = regionLink;
        this.photo = photo;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public BigDecimal getConstructArea() {
        return constructArea;
    }

    public void setConstructArea(BigDecimal constructArea) {
        this.constructArea = constructArea;
    }

    public Short getNumBlock() {
        return numBlock;
    }

    public void setNumBlock(Short numBlock) {
        this.numBlock = numBlock;
    }

    public String getNumFloors() {
        return numFloors;
    }

    public void setNumFloors(String numFloors) {
        this.numFloors = numFloors;
    }

    public Integer getNumApartment() {
        return numApartment;
    }

    public void setNumApartment(Integer numApartment) {
        this.numApartment = numApartment;
    }

    public String getApartmentArea() {
        return apartmentArea;
    }

    public void setApartmentArea(String apartmentArea) {
        this.apartmentArea = apartmentArea;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    public String getRegionLink() {
        return regionLink;
    }

    public void setRegionLink(String regionLink) {
        this.regionLink = regionLink;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public ConstructionContractor getConstructionContractor() {
        return constructionContractor;
    }

    public void setConstructionContractor(ConstructionContractor constructionContractor) {
        this.constructionContractor = constructionContractor;
    }

    public DesignUnit getDesignUnit() {
        return designUnit;
    }

    public void setDesignUnit(DesignUnit designUnit) {
        this.designUnit = designUnit;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
