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

import com.devcamp.real_estate_backend.model.DesignUnit;
import com.devcamp.real_estate_backend.service.DesignUnitService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/design-units")
public class DesignUnitController extends GenericController<DesignUnit>{
    @Autowired
    private DesignUnitService designUnitService;

    public DesignUnitController(DesignUnitService service) {
        super(service);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<DesignUnit> update(@PathVariable Integer id, @RequestBody DesignUnit masterLayout) {
        try {
            // Call the service to update the utility
            DesignUnit updatedMasterLayout = designUnitService.updateDesignUnit(id, masterLayout);
            return ResponseEntity.ok(updatedMasterLayout);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
