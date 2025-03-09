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

import com.devcamp.real_estate_backend.model.Investor;
import com.devcamp.real_estate_backend.service.InvestorService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/investors")
public class InvestorController extends GenericController<Investor>{
    @Autowired
    private InvestorService investorService;

    public InvestorController(InvestorService service) {
        super(service);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<Investor> update(@PathVariable Integer id, @RequestBody Investor regionLink) {
        try {
            // Call the service to update the utility
            Investor updatedRegionLink = investorService.updateRegionLink(id, regionLink);
            return ResponseEntity.ok(updatedRegionLink);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
