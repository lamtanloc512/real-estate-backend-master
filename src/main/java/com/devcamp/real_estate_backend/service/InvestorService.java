package com.devcamp.real_estate_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.Investor;
import com.devcamp.real_estate_backend.repository.InvestorRepository;

@Service
public class InvestorService extends GenericServiceImpl<Investor>{
    @Autowired
    private InvestorRepository investorRepository;

    public InvestorService(InvestorRepository repository) {
        super(repository);
    }

    public Investor updateRegionLink(Integer id, Investor updatedInvestor) {
        return investorRepository.findById(id).map(existingInvestor -> {
            // Update fields selectively from the body
            if (updatedInvestor.getName() != null) {
                existingInvestor.setName(updatedInvestor.getName());
            }
            if (updatedInvestor.getDescription() != null) {
                existingInvestor.setDescription(updatedInvestor.getDescription());
            }
            if (updatedInvestor.getAddress() != null) {
                existingInvestor.setAddress(updatedInvestor.getAddress());
            }
            if (updatedInvestor.getEmail() != null) {
                existingInvestor.setEmail(updatedInvestor.getEmail());
            }
            if (updatedInvestor.getFax() != null) {
                existingInvestor.setFax(updatedInvestor.getFax());
            }
            if (updatedInvestor.getNote() != null) {
                existingInvestor.setNote(updatedInvestor.getNote());
            }
            if (updatedInvestor.getPhone() != null) {
                existingInvestor.setPhone(updatedInvestor.getPhone());
            }
            if (updatedInvestor.getProjects() != null) {
                existingInvestor.setProjects(updatedInvestor.getProjects());
            }
            if (updatedInvestor.getWebsite() != null) {
                existingInvestor.setWebsite(updatedInvestor.getWebsite());
            }
            return investorRepository.save(existingInvestor);
        }).orElseThrow(() -> new RuntimeException("Investor not found with id " + id));
    }
}
