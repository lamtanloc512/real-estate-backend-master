package com.devcamp.real_estate_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.ConstructionContractor;
import com.devcamp.real_estate_backend.service.ConstructionContractorService;

@RestController
@RequestMapping("/api/v1/contractors")
@CrossOrigin
public class ConstructionContractorController {
    @Autowired
    private ConstructionContractorService contractorService;

    // Create a new contractor
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ConstructionContractor> createContractor(@RequestBody ConstructionContractor contractor) {
        ConstructionContractor createdContractor = contractorService.createContractor(contractor);
        return ResponseEntity.status(201).body(createdContractor);
    }

    // Get all contractors
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SALE')")
    public ResponseEntity<List<ConstructionContractor>> getAllContractors() {
        List<ConstructionContractor> contractors = contractorService.getAllContractors();
        return ResponseEntity.ok(contractors);
    }

    // Get a contractor by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<ConstructionContractor> getContractorById(@PathVariable Integer id) {
        return contractorService.getContractorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing contractor
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ConstructionContractor> updateContractor(
            @PathVariable Integer id, @RequestBody ConstructionContractor contractor) {
        try {
            ConstructionContractor updatedContractor = contractorService.updateContractor(id, contractor);
            return ResponseEntity.ok(updatedContractor);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a contractor
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteContractor(@PathVariable Integer id) {
        try {
            contractorService.deleteContractor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
