package com.devcamp.real_estate_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.real_estate_backend.model.Utility;

@Repository
public interface UtilityRepository extends JpaRepository<Utility, Integer>{

}
