package com.devcamp.real_estate_backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.devcamp.real_estate_backend.model.RealEstate;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Integer>, JpaSpecificationExecutor<RealEstate>{
    Page<RealEstate> findAll(Specification<RealEstate> spec, Pageable pageable);
    List<RealEstate> findByCreatedBy(String createdBy);
}
