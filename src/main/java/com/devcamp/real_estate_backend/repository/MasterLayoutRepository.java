package com.devcamp.real_estate_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.real_estate_backend.model.MasterLayout;

public interface MasterLayoutRepository extends JpaRepository<MasterLayout, Integer>{
    List<MasterLayout> findByProjectId(Integer projectId);
}
