package com.devcamp.real_estate_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devcamp.real_estate_backend.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{
    List<District> findByName(String name);

    @Query("SELECT d FROM District d WHERE d.name = :name AND d.province.name = :provinceName")
    List<District> findByNameAndProvince(@Param("name") String name, @Param("provinceName") String provinceName);
}
