package com.devcamp.real_estate_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.real_estate_backend.model.ConstructionContractor;

public interface ConstructionContractorRepository extends JpaRepository<ConstructionContractor, Integer>{
    
}
