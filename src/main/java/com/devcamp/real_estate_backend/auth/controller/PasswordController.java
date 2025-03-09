package com.devcamp.real_estate_backend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.auth.dto.ResetPasswordRequest;
import com.devcamp.real_estate_backend.auth.service.UserService;
import com.devcamp.real_estate_backend.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/reset-password/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE','CUSTOMER')")
    public ResponseEntity<?> resetUserPassword(@RequestBody ResetPasswordRequest request) {
        return resetPassword(request, userService);
    }

    @PostMapping("/reset-password/employee")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE','CUSTOMER')")
    public ResponseEntity<?> resetEmployeePassword(@RequestBody ResetPasswordRequest request) {
        return resetPassword(request, employeeService);
    }

    private ResponseEntity<?> resetPassword(ResetPasswordRequest request, UserDetailsService service) {
        UserDetails user = service.loadUserByUsername(request.getUsername());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Incorrect old password.");
        }

        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        if (service instanceof UserService) {
            ((UserService) service).updatePassword(request.getUsername(), encodedNewPassword);
        } else if (service instanceof EmployeeService) {
            ((EmployeeService) service).updatePassword(request.getUsername(), encodedNewPassword);
        }

        return ResponseEntity.ok("Password reset successfully.");
    }
}
