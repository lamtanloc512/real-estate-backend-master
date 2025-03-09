package com.devcamp.real_estate_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "realestate")
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is required")
    @Column(length = 2000)
    private String title;

    @Column
    private Integer type; // E.g., Land, Apartment, Office, etc.

    @Column
    private Integer request; // E.g., For Sale, For Rent, etc.

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "wards_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "street_id")
    private Street street;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false, length = 2000)
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(precision = 20, scale = 2)
    private BigDecimal price;

    @Column(name = "price_min", precision = 20, scale = 2)
    private BigDecimal priceMin;

    @Column
    private Integer priceTime; // E.g., Quick sale, regular sale, etc.

    @Column(name = "date_create", updatable = false)
    private LocalDateTime dateCreate;

    @PrePersist
    protected void onCreate() {
        dateCreate = LocalDateTime.now();
    }

    @Column(precision = 12, scale = 0)
    private BigDecimal acreage;

    @Column
    private Integer direction; // E.g., North, East, South, West, etc.

    @Column
    private Integer totalFloors;

    @Column
    private Integer numberFloors;

    @Column
    private Integer bath;

    @Column(length = 10)
    private String apartCode;

    @Column(precision = 12, scale = 2)
    private BigDecimal wallArea;

    @Column
    private Integer bedroom;

    @Column
    private Integer balcony;

    @Column(length = 255)
    private String landscapeView;

    @Column(name = "apart_loca")
    private Integer apartmentLocation; // E.g., Corner apartment, standard apartment, etc.

    @Column(name = "apart_type")
    private Integer apartmentType; // E.g., High-end, office, affordable.

    @Column(name = "furniture_type")
    private Integer furnitureType; // E.g., Basic, Fully furnished, Unknown.

    @Column(name = "price_rent")
    private Integer priceRent;

    @Column(name = "return_rate", precision = 3, scale = 2)
    private BigDecimal returnRate;

    @ManyToOne
    @JoinColumn(name = "legal_doc")
    private LegalDocument legalDocument;

    @Column(length = 2000)
    private String description;

    @Column(name = "width_y")
    private Integer width;

    @Column(name = "long_x")
    private Integer length;

    @Column(name = "street_house")
    private Boolean streetHouse;

    @Column(name = "FSBO")
    private Boolean forSaleByOwner;

    @Column(name = "view_num")
    private Integer viewNum;

    // @ManyToOne
    // @JoinColumn(name = "create_by")
    // private Customer createdBy;
    private String createdBy;

    // @ManyToOne
    // @JoinColumn(name = "update_by")
    // private Customer updatedBy;
    private String updatedBy;

    @Column(length = 200)
    private String shape;

    @Column(name = "distance2facade")
    private Integer distanceToFacade;

    @Column(name = "adjacent_facade_num")
    private Integer adjacentFacadeNum;

    @Column(length = 200)
    private String adjacentRoad;

    @Column(name = "alley_min_width")
    private Integer alleyMinWidth;

    @Column(name = "adjacent_alley_min_width")
    private Integer adjacentAlleyMinWidth;

    @Column(name = "factor")
    private Integer factor; // E.g., Near market, park, etc.

    @Column(length = 2000)
    private String structure;

    @Column(name = "DTSXD")
    private Integer constructionArea;

    @Column(name = "CLCL", precision = 2)
    private Integer constructionQuality;

    @Column(name = "CTXD_price")
    private Integer constructionPrice;

    @Column(name = "CTXD_value")
    private Integer constructionValue;

    @Column(length = 2000)
    private String photo;

    @Column(name = "_lat")
    private Double latitude;

    @Column(name = "_lng")
    private Double longitude;

    @Column(name = "approve_status", nullable = false)
    private String approveStatus = "Pending"; // Default value

    public RealEstate() {
    }

    public RealEstate(@NotBlank(message = "Title is required") String title, Integer type, Integer request,
            Province province, District district, Ward ward, Street street, Project project, String address,
            Customer customer, BigDecimal price, BigDecimal priceMin, Integer priceTime, BigDecimal acreage,
            Integer direction, Integer totalFloors, Integer numberFloors, Integer bath, String apartCode,
            BigDecimal wallArea, Integer bedroom, Integer balcony, String landscapeView, Integer apartmentLocation,
            Integer apartmentType, Integer furnitureType, Integer priceRent, BigDecimal returnRate,
            LegalDocument legalDocument, String description, Integer width, Integer length, Boolean streetHouse,
            Boolean forSaleByOwner, Integer viewNum, String createdBy, String updatedBy, String shape,
            Integer distanceToFacade, Integer adjacentFacadeNum, String adjacentRoad, Integer alleyMinWidth,
            Integer adjacentAlleyMinWidth, Integer factor, String structure, Integer constructionArea,
            Integer constructionQuality, Integer constructionPrice, Integer constructionValue, String photo,
            Double latitude, Double longitude) {
        this.title = title;
        this.type = type;
        this.request = request;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.street = street;
        this.project = project;
        this.address = address;
        this.customer = customer;
        this.price = price;
        this.priceMin = priceMin;
        this.priceTime = priceTime;
        this.acreage = acreage;
        this.direction = direction;
        this.totalFloors = totalFloors;
        this.numberFloors = numberFloors;
        this.bath = bath;
        this.apartCode = apartCode;
        this.wallArea = wallArea;
        this.bedroom = bedroom;
        this.balcony = balcony;
        this.landscapeView = landscapeView;
        this.apartmentLocation = apartmentLocation;
        this.apartmentType = apartmentType;
        this.furnitureType = furnitureType;
        this.priceRent = priceRent;
        this.returnRate = returnRate;
        this.legalDocument = legalDocument;
        this.description = description;
        this.width = width;
        this.length = length;
        this.streetHouse = streetHouse;
        this.forSaleByOwner = forSaleByOwner;
        this.viewNum = viewNum;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.shape = shape;
        this.distanceToFacade = distanceToFacade;
        this.adjacentFacadeNum = adjacentFacadeNum;
        this.adjacentRoad = adjacentRoad;
        this.alleyMinWidth = alleyMinWidth;
        this.adjacentAlleyMinWidth = adjacentAlleyMinWidth;
        this.factor = factor;
        this.structure = structure;
        this.constructionArea = constructionArea;
        this.constructionQuality = constructionQuality;
        this.constructionPrice = constructionPrice;
        this.constructionValue = constructionValue;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRequest() {
        return request;
    }

    public void setRequest(Integer request) {
        this.request = request;
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

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceTime() {
        return priceTime;
    }

    public void setPriceTime(Integer priceTime) {
        this.priceTime = priceTime;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(Integer totalFloors) {
        this.totalFloors = totalFloors;
    }

    public Integer getNumberFloors() {
        return numberFloors;
    }

    public void setNumberFloors(Integer numberFloors) {
        this.numberFloors = numberFloors;
    }

    public Integer getBath() {
        return bath;
    }

    public void setBath(Integer bath) {
        this.bath = bath;
    }

    public String getApartCode() {
        return apartCode;
    }

    public void setApartCode(String apartCode) {
        this.apartCode = apartCode;
    }

    public BigDecimal getWallArea() {
        return wallArea;
    }

    public void setWallArea(BigDecimal wallArea) {
        this.wallArea = wallArea;
    }

    public Integer getBedroom() {
        return bedroom;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public Integer getBalcony() {
        return balcony;
    }

    public void setBalcony(Integer balcony) {
        this.balcony = balcony;
    }

    public String getLandscapeView() {
        return landscapeView;
    }

    public void setLandscapeView(String landscapeView) {
        this.landscapeView = landscapeView;
    }

    public Integer getApartmentLocation() {
        return apartmentLocation;
    }

    public void setApartmentLocation(Integer apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
    }

    public Integer getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(Integer apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Integer getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(Integer furnitureType) {
        this.furnitureType = furnitureType;
    }

    public Integer getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(Integer priceRent) {
        this.priceRent = priceRent;
    }

    public BigDecimal getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    public LegalDocument getLegalDocument() {
        return legalDocument;
    }

    public void setLegalDocument(LegalDocument legalDocument) {
        this.legalDocument = legalDocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getStreetHouse() {
        return streetHouse;
    }

    public void setStreetHouse(Boolean streetHouse) {
        this.streetHouse = streetHouse;
    }

    public Boolean getForSaleByOwner() {
        return forSaleByOwner;
    }

    public void setForSaleByOwner(Boolean forSaleByOwner) {
        this.forSaleByOwner = forSaleByOwner;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    // public Customer getCreatedBy() {
    //     return createdBy;
    // }

    // public void setCreatedBy(Customer createdBy) {
    //     this.createdBy = createdBy;
    // }

    // public Customer getUpdatedBy() {
    //     return updatedBy;
    // }

    // public void setUpdatedBy(Customer updatedBy) {
    //     this.updatedBy = updatedBy;
    // }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Integer getDistanceToFacade() {
        return distanceToFacade;
    }

    public void setDistanceToFacade(Integer distanceToFacade) {
        this.distanceToFacade = distanceToFacade;
    }

    public Integer getAdjacentFacadeNum() {
        return adjacentFacadeNum;
    }

    public void setAdjacentFacadeNum(Integer adjacentFacadeNum) {
        this.adjacentFacadeNum = adjacentFacadeNum;
    }

    public String getAdjacentRoad() {
        return adjacentRoad;
    }

    public void setAdjacentRoad(String adjacentRoad) {
        this.adjacentRoad = adjacentRoad;
    }

    public Integer getAlleyMinWidth() {
        return alleyMinWidth;
    }

    public void setAlleyMinWidth(Integer alleyMinWidth) {
        this.alleyMinWidth = alleyMinWidth;
    }

    public Integer getAdjacentAlleyMinWidth() {
        return adjacentAlleyMinWidth;
    }

    public void setAdjacentAlleyMinWidth(Integer adjacentAlleyMinWidth) {
        this.adjacentAlleyMinWidth = adjacentAlleyMinWidth;
    }

    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Integer constructionArea) {
        this.constructionArea = constructionArea;
    }

    public Integer getConstructionQuality() {
        return constructionQuality;
    }

    public void setConstructionQuality(Integer constructionQuality) {
        this.constructionQuality = constructionQuality;
    }

    public Integer getConstructionPrice() {
        return constructionPrice;
    }

    public void setConstructionPrice(Integer constructionPrice) {
        this.constructionPrice = constructionPrice;
    }

    public Integer getConstructionValue() {
        return constructionValue;
    }

    public void setConstructionValue(Integer constructionValue) {
        this.constructionValue = constructionValue;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
    
}
