package com.devcamp.real_estate_backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.model.Employee;
import com.devcamp.real_estate_backend.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Create a new Employee
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try{
            Employee createdEmployee = employeeService.createEmployee(employee);
            return ResponseEntity.status(201).body(createdEmployee);
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
    }

    // Get all Employees
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> Employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(Employees);
    }

    // Get Employee by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing Employee
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Employee
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/is-employee/{username}")
    public ResponseEntity<?> isEmployee(@PathVariable String username) {
        boolean isEmployee = employeeService.existsByUsername(username);
        return ResponseEntity.ok(Map.of("username", username, "isEmployee", isEmployee));
    }

    @GetMapping("/employee-role/{username}")
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ResponseEntity<?> getEmployeeRole(@PathVariable String username) {
        Optional<String> role = employeeService.getEmployeeRoleByUsername(username);
        
        if (role.isPresent()) {
            return ResponseEntity.ok(Map.of("username", username, "role", role.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Employee not found"));
        }
    }
}
