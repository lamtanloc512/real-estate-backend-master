package com.devcamp.real_estate_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.District;
import com.devcamp.real_estate_backend.model.Province;
import com.devcamp.real_estate_backend.service.ProvinceService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;

    @GetMapping("/provinces")
    public ResponseEntity<List<Province>> getProvinces(
                        @RequestParam(required = false) Integer page, 
                        @RequestParam(required = false) Integer size) {
        try{
            List<Province> provinces;

            if(page != null && size != null){
                provinces = provinceService.getProvincesPerPage(page, size);
            }
            else {
                provinces = provinceService.getAllProvinces();
            }

            return new ResponseEntity<>(provinces, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/districts")
    public ResponseEntity<List<District>> getDistrictByProvinceCode(@RequestParam String provinceCode) {
        try{
            List<District> districts = provinceService.getDistrictByProvinceCode(provinceCode);
            if(districts != null && !districts.isEmpty()){
                return new ResponseEntity<>(districts, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/provinces/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createProvince(@Valid @RequestBody Province provinceJson) {
        try{
            Province createdProvince = provinceService.createProvince(provinceJson);
            return new ResponseEntity<>(createdProvince, HttpStatus.CREATED);
        }
        catch(Exception ex){
            return ResponseEntity.unprocessableEntity().body("Failed to craete specified province: " + ex.getCause().getMessage());
        }
    }

    @PutMapping("/provinces/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateProvince(@PathVariable("id") Long id, @Valid @RequestBody Province provinceJson) {
        try{
            Province updatedProvince = provinceService.updateProvince(id, provinceJson);
            if(updatedProvince != null){
                return new ResponseEntity<>(updatedProvince, HttpStatus.OK);
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
    
    @DeleteMapping("/provinces/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteProvince(@PathVariable("id") Long id) {
        try{
            Object result = provinceService.deleteProvinceById(id);
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
