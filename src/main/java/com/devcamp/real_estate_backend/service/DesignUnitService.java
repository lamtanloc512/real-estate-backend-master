package com.devcamp.real_estate_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.DesignUnit;
import com.devcamp.real_estate_backend.repository.DesignUnitRepository;

@Service
public class DesignUnitService extends GenericServiceImpl<DesignUnit> {
    @Autowired
    private DesignUnitRepository designUnitRepository;

    public DesignUnitService(DesignUnitRepository repository) {
        super(repository);
    }

    public DesignUnit updateDesignUnit(Integer id, DesignUnit updatedDesignUnit) {
        return designUnitRepository.findById(id).map(existingDesignUnit -> {
            // Update fields selectively from the body
            if (updatedDesignUnit.getName() != null) {
                existingDesignUnit.setName(updatedDesignUnit.getName());
            }
            if (updatedDesignUnit.getDescription() != null) {
                existingDesignUnit.setDescription(updatedDesignUnit.getDescription());
            }
            if (updatedDesignUnit.getAddress() != null) {
                existingDesignUnit.setAddress(updatedDesignUnit.getAddress());
            }
            if (updatedDesignUnit.getEmail() != null) {
                existingDesignUnit.setEmail(updatedDesignUnit.getEmail());
            }
            if (updatedDesignUnit.getFax() != null) {
                existingDesignUnit.setFax(updatedDesignUnit.getFax());
            }
            if (updatedDesignUnit.getNote() != null) {
                existingDesignUnit.setNote(updatedDesignUnit.getNote());
            }
            if (updatedDesignUnit.getPhone() != null) {
                existingDesignUnit.setPhone(updatedDesignUnit.getPhone());
            }
            if (updatedDesignUnit.getProjects() != null) {
                existingDesignUnit.setProjects(updatedDesignUnit.getProjects());
            }
            if (updatedDesignUnit.getWebsite() != null) {
                existingDesignUnit.setWebsite(updatedDesignUnit.getWebsite());
            }
            return designUnitRepository.save(existingDesignUnit);
        }).orElseThrow(() -> new RuntimeException("Utility not found with id " + id));
    }
}
