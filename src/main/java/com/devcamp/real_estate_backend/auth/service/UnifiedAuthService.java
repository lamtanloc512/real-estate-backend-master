package com.devcamp.real_estate_backend.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.auth.entity.User;
import com.devcamp.real_estate_backend.auth.repository.UserRepository;
import com.devcamp.real_estate_backend.auth.security.UnifiedPrincipal;
import com.devcamp.real_estate_backend.model.Employee;
import com.devcamp.real_estate_backend.repository.EmployeeRepository;

@Service
@Primary
public class UnifiedAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check in users first
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new UnifiedPrincipal(
                user.get().getId(),
                user.get().getUsername(),
                user.get().getPassword(),
                user.get().getRole().getName()
            );
        }

        // Check in employees
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if (employee.isPresent()) {
            return new UnifiedPrincipal(
                employee.get().getEmployeeId(),
                employee.get().getUsername(),
                employee.get().getPassword(),
                employee.get().getRole().getName()
            );
        }

        throw new UsernameNotFoundException("Username not found: " + username);
    }
}