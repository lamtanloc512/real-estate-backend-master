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

import com.devcamp.real_estate_backend.model.RegionLink;
import com.devcamp.real_estate_backend.service.RegionLinkService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/region-links")
public class RegionLinkController extends GenericController<RegionLink>{
    @Autowired
    private RegionLinkService regionLinkService;

    public RegionLinkController(RegionLinkService service) {
        super(service);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<RegionLink> update(@PathVariable Integer id, @RequestBody RegionLink regionLink) {
        try {
            // Call the service to update the utility
            RegionLink updatedRegionLink = regionLinkService.updateRegionLink(id, regionLink);
            return ResponseEntity.ok(updatedRegionLink);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
