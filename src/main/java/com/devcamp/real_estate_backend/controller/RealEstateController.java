package com.devcamp.real_estate_backend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.RealEstate;
import com.devcamp.real_estate_backend.service.RealEstateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/realestate")
public class RealEstateController {
    private static final Logger logger = LoggerFactory.getLogger(RealEstateController.class);

    @Autowired
    private RealEstateService realEstateService;

    @GetMapping()
    public ResponseEntity<Page<RealEstate>> getRealEstatesPerPage(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        try{
            return ResponseEntity.ok(realEstateService.getRealEstatesPerPage(page, size));
        }
        catch(Exception ex){
            logger.error("Error loading real estate of page " + page + 1 + ": ", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RealEstate> getRealEstateById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(realEstateService.getRealEstateById(id));
        }
        catch(Exception ex){
            logger.error("Error loading real estate id " + id + ": ", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("/filter")
    public ResponseEntity<Page<RealEstate>> filterRealEstate(@RequestParam(required = false) Integer provinceId,
                                                            @RequestParam(required = false) Integer districtId,
                                                            @RequestParam(required = false) Integer wardId,
                                                            @RequestParam(required = false) Integer projectId,
                                                            @RequestParam(required = false) Integer type,
                                                            @RequestParam(required = false) BigDecimal minPrice,
                                                            @RequestParam(required = false) BigDecimal maxPrice,
                                                            @RequestParam(required = false) Integer bedrooms,
                                                            @RequestParam(required = false) BigDecimal minAcreage,
                                                            @RequestParam(required = false) BigDecimal maxAcreage,
                                                            @RequestParam(required = false) String createdBy,
                                                            @RequestParam(required = false) Boolean isForSaleByOwner,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(required = false) String approveStatus) {
        try{
            Page<RealEstate> filteredRealEstate = realEstateService.filterRealEstate(
                        provinceId, districtId, wardId, projectId, type, minPrice, maxPrice, 
                        bedrooms, minAcreage, maxAcreage, page, size, createdBy, isForSaleByOwner, approveStatus);
            return ResponseEntity.ok(filteredRealEstate);
        }
        catch(Exception ex){
            logger.error("Error loading real estate ", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // POST API to create a new RealEstate record
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','SALE','CUSTOMER')")
    public ResponseEntity<RealEstate> createRealEstate(@RequestBody RealEstate realEstate) {
        RealEstate createdRealEstate = realEstateService.createRealEstate(realEstate);
        return ResponseEntity.status(201).body(createdRealEstate);
    }

    // PUT API to update an existing RealEstate record
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SALE','CUSTOMER')")
    public ResponseEntity<RealEstate> updateRealEstate(
            @PathVariable Integer id, 
            @RequestBody RealEstate realEstate) {
        RealEstate updatedRealEstate = realEstateService.updateRealEstate(id, realEstate);
        return ResponseEntity.ok(updatedRealEstate);
    }

    // Delete a realestate record
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SALE','CUSTOMER')")
    public ResponseEntity<Void> deleteRealestate(@PathVariable Integer id) {
        try {
            realEstateService.deleteRealestate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ResponseEntity<RealEstate> approveRealEstate(@PathVariable Integer id, @RequestParam String status) {
        try {
            RealEstate updatedRealEstate = realEstateService.updateApproveStatus(id, status);
            return ResponseEntity.ok(updatedRealEstate);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ResponseEntity<List<RealEstate>> getPropertiesByUser(@PathVariable String userId) {
        List<RealEstate> properties = realEstateService.getPropertiesByCreatedBy(userId);
        return ResponseEntity.ok(properties);
    }
}
