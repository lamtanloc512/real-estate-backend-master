package com.devcamp.real_estate_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.real_estate_backend.model.Ward;

public interface WardRepository extends JpaRepository<Ward, Integer>{

}
