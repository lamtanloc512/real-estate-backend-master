package com.devcamp.real_estate_backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.MasterLayout;
import com.devcamp.real_estate_backend.service.MasterLayoutService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/master-layout")
public class MasterLayoutController extends GenericController<MasterLayout>{
    private static final Logger logger = LoggerFactory.getLogger(MasterLayoutController.class);

    @Autowired
    private MasterLayoutService masterLayoutService;

    public MasterLayoutController(MasterLayoutService service) {
        super(service);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE')")
    public ResponseEntity<MasterLayout> update(@PathVariable Integer id, @RequestBody MasterLayout masterLayout) {
        try {
            // Call the service to update the utility
            MasterLayout updatedMasterLayout = masterLayoutService.updateMasterLayout(id, masterLayout);
            return ResponseEntity.ok(updatedMasterLayout);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-project")
    public ResponseEntity<List<MasterLayout>> getMasterLayoutByProjectId(@RequestParam Integer projectId) {
        try{
            return ResponseEntity.ok(masterLayoutService.getMasterLayoutByProjectId(projectId));
        }
        catch(Exception ex){
            logger.error("Error loading master layout " , ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
