package com.devcamp.real_estate_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.real_estate_backend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
    Optional<Employee> findByUsername(String username);
}
