package com.devcamp.real_estate_backend.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.repository.RealEstateRepository;
import com.devcamp.real_estate_backend.specification.RealEstateSpecification;

import com.devcamp.real_estate_backend.model.RealEstate;

@Service
public class RealEstateService {
    @Autowired
    private RealEstateRepository realEstateRepository;

    public Page<RealEstate> getRealEstatesPerPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return realEstateRepository.findAll(pageable);
    }

    public RealEstate getRealEstateById(int id){
        Optional<RealEstate> realEstate = realEstateRepository.findById(id);
        if(realEstate.isPresent()){
            return realEstate.get();
        }
        return null;
    }

    public Page<RealEstate> filterRealEstate(Integer provinceId, Integer districtId, Integer wardId, Integer projectId,
                                            Integer type, BigDecimal minPrice, BigDecimal maxPrice, Integer bedrooms,
                                            BigDecimal minAcreage, BigDecimal maxAcreage, Integer page, Integer size,
                                            String createdBy, Boolean isForSaleByOwner, String approveStatus){
        Specification<RealEstate> spec = Specification.where(RealEstateSpecification.hasProvince(provinceId))
                                                    .and(RealEstateSpecification.hasDistrict(districtId))
                                                    .and(RealEstateSpecification.hasWard(wardId))
                                                    .and(RealEstateSpecification.hasProject(projectId))
                                                    .and(RealEstateSpecification.hasType(type))
                                                    .and(RealEstateSpecification.hasPriceRange(minPrice, maxPrice))
                                                    .and(RealEstateSpecification.hasBedRooms(bedrooms))
                                                    .and(RealEstateSpecification.hasAcreageRange(minAcreage, maxAcreage))
                                                    .and(RealEstateSpecification.hasCreatedBy(createdBy))
                                                    .and(RealEstateSpecification.isForSaleOwner(isForSaleByOwner))
                                                    .and(RealEstateSpecification.hasApprovalStatus(approveStatus));
        Pageable pageable = PageRequest.of(page, size);
        return realEstateRepository.findAll(spec, pageable);
    }

    // Create a new RealEstate record
    public RealEstate createRealEstate(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    // Update an existing RealEstate record
    public RealEstate updateRealEstate(Integer id, RealEstate updatedRealEstate) {
        return realEstateRepository.findById(id).map(existingRealEstate -> {
            existingRealEstate.setTitle(updatedRealEstate.getTitle());
            existingRealEstate.setType(updatedRealEstate.getType());
            existingRealEstate.setRequest(updatedRealEstate.getRequest());
            existingRealEstate.setProvince(updatedRealEstate.getProvince());
            existingRealEstate.setDistrict(updatedRealEstate.getDistrict());
            existingRealEstate.setWard(updatedRealEstate.getWard());
            existingRealEstate.setStreet(updatedRealEstate.getStreet());
            existingRealEstate.setProject(updatedRealEstate.getProject());
            existingRealEstate.setAddress(updatedRealEstate.getAddress());
            existingRealEstate.setPrice(updatedRealEstate.getPrice());
            existingRealEstate.setPriceMin(updatedRealEstate.getPriceMin());
            existingRealEstate.setPriceTime(updatedRealEstate.getPriceTime());
            existingRealEstate.setAcreage(updatedRealEstate.getAcreage());
            existingRealEstate.setDescription(updatedRealEstate.getDescription());
            existingRealEstate.setBath(updatedRealEstate.getBath());
            existingRealEstate.setBedroom(updatedRealEstate.getBedroom());
            existingRealEstate.setCustomer(updatedRealEstate.getCustomer());
            existingRealEstate.setTotalFloors(updatedRealEstate.getTotalFloors());
            existingRealEstate.setNumberFloors(updatedRealEstate.getNumberFloors());
            existingRealEstate.setBalcony(updatedRealEstate.getBalcony());
            existingRealEstate.setWidth(updatedRealEstate.getWidth());
            existingRealEstate.setLength(updatedRealEstate.getLength());
            existingRealEstate.setLegalDocument(updatedRealEstate.getLegalDocument());
            existingRealEstate.setPhoto(updatedRealEstate.getPhoto());
            existingRealEstate.setViewNum(updatedRealEstate.getViewNum());
            existingRealEstate.setApproveStatus("Pending");
            // Update other fields as necessary
            return realEstateRepository.save(existingRealEstate);
        }).orElseThrow(() -> new RuntimeException("RealEstate not found with id " + id));
    }

    // Delete a realestate record
    public void deleteRealestate(Integer id) {
        if (realEstateRepository.existsById(id)) {
            realEstateRepository.deleteById(id);
        } else {
            throw new RuntimeException("Property not found with id " + id);
        }
    }

    public RealEstate updateApproveStatus(Integer id, String status) {
        return realEstateRepository.findById(id).map(realEstate -> {
            if (!status.equals("Approved") && !status.equals("Declined")) {
                throw new IllegalArgumentException("Invalid status value");
            }
            realEstate.setApproveStatus(status);
            return realEstateRepository.save(realEstate);
        }).orElseThrow(() -> new RuntimeException("RealEstate not found with id " + id));
    }

    public List<RealEstate> getPropertiesByCreatedBy(String userId) {
        return realEstateRepository.findByCreatedBy(userId);
    }
}
