package com.devcamp.real_estate_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.Customer;
import com.devcamp.real_estate_backend.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer); // create_date set by @PrePersist
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setContactName(updatedCustomer.getContactName());
            existingCustomer.setContactTitle(updatedCustomer.getContactTitle());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setMobile(updatedCustomer.getMobile());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setNote(updatedCustomer.getNote());
            // update_date set by @PreUpdate
            return customerRepository.save(existingCustomer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    // Get all customers
    public Page<Customer> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable);
    }

    // Get a customer by ID
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    // Delete a customer
    public void deleteCustomer(Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }
}
