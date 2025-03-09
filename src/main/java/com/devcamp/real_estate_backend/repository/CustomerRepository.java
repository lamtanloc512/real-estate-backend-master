package com.devcamp.real_estate_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.real_estate_backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);
}
