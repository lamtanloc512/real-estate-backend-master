package com.devcamp.real_estate_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.Ward;
import com.devcamp.real_estate_backend.service.WardService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@CrossOrigin
@RequestMapping("/api/v1/wards")
public class WardController {
    @Autowired
    WardService wardService;

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable("id") Long id) {
        try{
            Ward ward = wardService.getWardById(id);
            if(ward != null){
                return new ResponseEntity<>(ward, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createWardByDistrictName(@RequestParam String districtName,
                                                    @RequestParam String provinceName, 
                                                    @Valid @RequestBody Ward wardJson) {
        try{
            Ward createdWard = wardService.createWard(districtName, provinceName, wardJson);
            return new ResponseEntity<>(createdWard, HttpStatus.OK);
        }
        catch(Exception ex){
            return ResponseEntity.unprocessableEntity().body("Failed to create specified district: " + ex.getCause().getMessage());
        }   
    }
    
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateWardById(@PathVariable Long id, @Valid @RequestBody Ward wardJson) {
        try{
            Ward updatedWard = wardService.updateWard(id, wardJson);
            if(updatedWard != null){
                return new ResponseEntity<>(updatedWard, HttpStatus.OK);
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

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteWardById(@PathVariable("id") Long id){
        try{
            Object result = wardService.deleteWardById(id);
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
