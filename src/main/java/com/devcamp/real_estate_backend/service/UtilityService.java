package com.devcamp.real_estate_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.Utility;
import com.devcamp.real_estate_backend.repository.UtilityRepository;

@Service
public class UtilityService extends GenericServiceImpl<Utility>{
    
    @Autowired
    private UtilityRepository utilityRepository;

    public UtilityService(UtilityRepository repository) {
        super(repository);
    }

    public Utility updateUtility(Integer id, Utility updatedUtility) {
        return utilityRepository.findById(id).map(existingUtility -> {
            // Update fields selectively from the body
            if (updatedUtility.getName() != null) {
                existingUtility.setName(updatedUtility.getName());
            }
            if (updatedUtility.getDescription() != null) {
                existingUtility.setDescription(updatedUtility.getDescription());
            }
            if (updatedUtility.getPhoto() != null) {
                existingUtility.setPhoto(updatedUtility.getPhoto());
            }
            return utilityRepository.save(existingUtility);
        }).orElseThrow(() -> new RuntimeException("Utility not found with id " + id));
    }
}
