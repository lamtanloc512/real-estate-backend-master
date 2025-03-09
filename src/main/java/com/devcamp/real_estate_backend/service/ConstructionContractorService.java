package com.devcamp.real_estate_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.ConstructionContractor;
import com.devcamp.real_estate_backend.repository.ConstructionContractorRepository;

@Service
public class ConstructionContractorService {
    @Autowired
    private ConstructionContractorRepository contractorRepository;

    // Create a new contractor
    public ConstructionContractor createContractor(ConstructionContractor contractor) {
        return contractorRepository.save(contractor);
    }

    // Get all contractors
    public List<ConstructionContractor> getAllContractors() {
        return contractorRepository.findAll();
    }

    // Get a contractor by ID
    public Optional<ConstructionContractor> getContractorById(Integer id) {
        return contractorRepository.findById(id);
    }

    // Update an existing contractor
    public ConstructionContractor updateContractor(Integer id, ConstructionContractor updatedContractor) {
        return contractorRepository.findById(id).map(existingContractor -> {
            existingContractor.setName(updatedContractor.getName());
            existingContractor.setDescription(updatedContractor.getDescription());
            existingContractor.setProjects(updatedContractor.getProjects());
            existingContractor.setAddress(updatedContractor.getAddress());
            existingContractor.setPhone(updatedContractor.getPhone());
            existingContractor.setPhone2(updatedContractor.getPhone2());
            existingContractor.setFax(updatedContractor.getFax());
            existingContractor.setEmail(updatedContractor.getEmail());
            existingContractor.setWebsite(updatedContractor.getWebsite());
            existingContractor.setNote(updatedContractor.getNote());
            return contractorRepository.save(existingContractor);
        }).orElseThrow(() -> new RuntimeException("Contractor not found with id " + id));
    }

    // Delete a contractor
    public void deleteContractor(Integer id) {
        if (contractorRepository.existsById(id)) {
            contractorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contractor not found with id " + id);
        }
    }
}
