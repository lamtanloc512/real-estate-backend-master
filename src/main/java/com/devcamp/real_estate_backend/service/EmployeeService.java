package com.devcamp.real_estate_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.model.Employee;
import com.devcamp.real_estate_backend.repository.EmployeeRepository;
import com.devcamp.real_estate_backend.auth.repository.UserRepository;
import com.devcamp.real_estate_backend.auth.security.UnifiedPrincipal;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String generateEmployeeId() {
        long count = employeeRepository.count() + 1; // Get total employees and increment
        return "emp" + count;
    }

    public Employee createEmployee(Employee employee) {
        // Check if username already exists in Users or Employees table
        if (employeeRepository.findByUsername(employee.getUsername()).isPresent() || 
            userRepository.findByUsername(employee.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken.");
        }

        employee.setEmployeeId(generateEmployeeId());  // Set custom ID
        // Encode password before saving
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        return employeeRepository.save(employee); // create_date set by @PrePersist
    }

    public Employee updateEmployee(String id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setTitle(updatedEmployee.getTitle());
            existingEmployee.setAddress(updatedEmployee.getAddress());
            existingEmployee.setHomePhone(updatedEmployee.getHomePhone());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setNotes(updatedEmployee.getNotes());
            existingEmployee.setBirthDate(updatedEmployee.getBirthDate());
            existingEmployee.setHireDate(updatedEmployee.getHireDate());
            existingEmployee.setUserLevel(updatedEmployee.getUserLevel());
            existingEmployee.setReportsTo(updatedEmployee.getReportsTo());
            existingEmployee.setRole(updatedEmployee.getRole());

            // Encode password only if it's updated
            if (updatedEmployee.getPassword() != null && !updatedEmployee.getPassword().isEmpty()) {
                existingEmployee.setPassword(passwordEncoder.encode(updatedEmployee.getPassword()));
            }
            
            return employeeRepository.save(existingEmployee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get a Employee by ID
    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    // Delete a employee
    public void deleteEmployee(String id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with id " + id);
        }
    }

    public void updatePassword(String username, String newPassword) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setPassword(newPassword);
        employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
        return new UnifiedPrincipal(employee.getEmployeeId(), employee.getUsername(), employee.getPassword(),
                employee.getRole().getName());
    }

    public boolean existsByUsername(String username) {
        return employeeRepository.findByUsername(username).isPresent();
    }

    public Optional<String> getEmployeeIdByUsername(String username) {
        return employeeRepository.findByUsername(username).map(Employee::getEmployeeId);
    }

    public Optional<String> getEmployeeRoleByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .map(employee -> employee.getRole().getName()); // Assuming Role has a `getName()` method
    }
}
