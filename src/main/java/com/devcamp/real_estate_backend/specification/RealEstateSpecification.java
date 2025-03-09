package com.devcamp.real_estate_backend.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.devcamp.real_estate_backend.model.RealEstate;

public class RealEstateSpecification {
    public static Specification<RealEstate> hasProvince(Integer provinceId){
        return (root, query, criteriaBuilder) ->
            provinceId == null ? null : criteriaBuilder.equal(root.get("province").get("id"), provinceId);
    }

    public static Specification<RealEstate> hasDistrict(Integer districtId){
        return (root, query, criteriaBuilder) ->
            districtId == null ? null : criteriaBuilder.equal(root.get("district").get("id"), districtId);
    }

    public static Specification<RealEstate> hasWard(Integer wardId){
        return (root, query, criteriaBuilder) ->
            wardId == null ? null : criteriaBuilder.equal(root.get("ward").get("id"), wardId);
    }

    public static Specification<RealEstate> hasProject(Integer projectId){
        return (root, query, criteriaBuilder) ->
            projectId == null ? null : criteriaBuilder.equal(root.get("project").get("id"), projectId);
    }

    public static Specification<RealEstate> hasType(Integer type){
        return (root, query, criteriaBuilder) ->
            type == null ? null : criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<RealEstate> hasPriceRange(BigDecimal minPrice, BigDecimal maxPrice){
        return (root, query, criteriaBuilder) -> {
            if(minPrice != null && maxPrice != null){
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            } else if(minPrice != null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else if(maxPrice != null){
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
            return null;
        };
    }

    public static Specification<RealEstate> hasBedRooms(Integer bedrooms){
        return (root, query, criteriaBuilder) ->
            bedrooms == null ? null : criteriaBuilder.equal(root.get("bedroom"), bedrooms);
    }

    public static Specification<RealEstate> hasAcreageRange(BigDecimal minAcreage, BigDecimal maxAcreage){
        return(root, query, criteriaBuilder) ->{
            if(minAcreage != null && maxAcreage != null){
                return criteriaBuilder.between(root.get("acreage"), minAcreage, maxAcreage);
            } else if(minAcreage != null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("acreage"), minAcreage);
            } else if(maxAcreage != null){
                return criteriaBuilder.lessThanOrEqualTo(root.get("acreage"), maxAcreage);
            }
            return null;
        };
    }

    public static Specification<RealEstate> hasCreatedBy(String createdBy){
        return(root, query, criteriaBuilder) ->
            createdBy == null ? null : criteriaBuilder.equal(root.get("createdBy"), createdBy);
    }

    public static Specification<RealEstate> isForSaleOwner(Boolean forSaleByOwner){
        return(root, query, criteriaBuilder) ->
            forSaleByOwner == null ? null : criteriaBuilder.equal(root.get("forSaleByOwner"), forSaleByOwner);
    }

    public static Specification<RealEstate> hasApprovalStatus (String approveStatus){
        return(root, query, criteriaBuilder) ->
            approveStatus == null ? null : criteriaBuilder.equal(root.get("approveStatus"), approveStatus);
    }
}
