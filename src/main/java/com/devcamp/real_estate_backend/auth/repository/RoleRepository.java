package com.devcamp.real_estate_backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.real_estate_backend.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
