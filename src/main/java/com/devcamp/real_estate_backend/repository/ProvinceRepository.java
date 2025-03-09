package com.devcamp.real_estate_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.real_estate_backend.model.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer>{
    Province findByCode(String code);
}
