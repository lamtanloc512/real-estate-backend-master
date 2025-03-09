package com.devcamp.real_estate_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.District;
import com.devcamp.real_estate_backend.model.Ward;
import com.devcamp.real_estate_backend.service.DistrictService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class DistrictController {
    @Autowired
    DistrictService districtService;

    @GetMapping("/wards")
    public ResponseEntity<List<Ward>> getWardsByDistrictName(@RequestParam String districtName,
                                                            @RequestParam(required = false) String provinceName) {
        try{
            List<Ward> wards ;
            if (provinceName != null) {
                wards = districtService.getWardsByDistrictNameAndProvince(districtName, provinceName);
            } else {
                wards = districtService.getWardByDistrictName(districtName);
            }

            if(!wards.isEmpty()){
                return new ResponseEntity<>(wards, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable("id") Long id) {
        try{
            District district = districtService.getDistrictById(id);
            if(district != null){
                return new ResponseEntity<>(district, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/districts/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createDistrict(@RequestParam String proviceCode, 
                                            @Valid @RequestBody District districtJson) {
        try{
            District createdDistrict = districtService.createDistrict(proviceCode, districtJson);
            return new ResponseEntity<>(createdDistrict, HttpStatus.OK);
        }
        catch(Exception ex){
            return ResponseEntity.unprocessableEntity().body("Failed to create specified district: " + ex.getCause().getMessage());
        }   
    }
    
    @PutMapping("/districts/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateDistrict(@PathVariable("id") Long id, @Valid @RequestBody District districtJson) {
        try{
            District updatedDistrict = districtService.updateDistrict(id, districtJson);
            if(updatedDistrict != null){
                return new ResponseEntity<>(updatedDistrict, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception ex){
            return ResponseEntity.unprocessableEntity()
                    .body("Can not execute operation of this Entity" + ex.getCause().getMessage());
        }
    }
    
    @DeleteMapping("/districts/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteDistrictById(@PathVariable("id") Long id) {
        try{
            Object result = districtService.deleteDistrictById(id);
            if(result != null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception ex){
            return ResponseEntity.unprocessableEntity()
                    .body("Can not execute operation of this entity" + ex.getCause().getMessage());
        }
    }
     
    
}
