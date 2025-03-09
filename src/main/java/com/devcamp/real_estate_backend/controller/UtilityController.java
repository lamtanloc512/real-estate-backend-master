package com.devcamp.real_estate_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.Utility;
import com.devcamp.real_estate_backend.service.UtilityService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/utilities")
public class UtilityController extends GenericController<Utility>{

    @Autowired
    private UtilityService utilityService;

    public UtilityController(UtilityService service) {
        super(service);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<Utility> update(@PathVariable Integer id, @RequestBody Utility utility) {
        try {
            // Call the service to update the utility
            Utility updatedUtility = utilityService.updateUtility(id, utility);
            return ResponseEntity.ok(updatedUtility);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
