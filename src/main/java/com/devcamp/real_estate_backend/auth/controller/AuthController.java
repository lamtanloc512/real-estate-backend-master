package com.devcamp.real_estate_backend.auth.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.real_estate_backend.auth.dto.LoginRequest;
import com.devcamp.real_estate_backend.auth.dto.LoginResponse;
import com.devcamp.real_estate_backend.auth.dto.RegisterRequest;
import com.devcamp.real_estate_backend.auth.entity.User;
import com.devcamp.real_estate_backend.auth.security.JwtUtil;
import com.devcamp.real_estate_backend.auth.service.TokenService;
import com.devcamp.real_estate_backend.auth.service.UnifiedAuthService;
import com.devcamp.real_estate_backend.auth.service.UserService;
import com.devcamp.real_estate_backend.model.ConstructionContractor;
import com.devcamp.real_estate_backend.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UnifiedAuthService authService;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;
    private final UserService userService;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UnifiedAuthService authService,
            JwtUtil jwtUtil,
            TokenService tokenService,
            UserService userService,
            EmployeeService employeeService,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.tokenService = tokenService;
        this.userService = userService;
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        // Check if the username exists in both User and Employee tables
        if (userService.existsByUsername(request.getUsername())
                || employeeService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        // Generate custom user ID
        String userId = userService.generateUserId(); 
        // Encode password before saving
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create new User
        User newUser = new User();
        newUser.setId(userId);  // Set custom ID
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setEmail(request.getEmail());
        newUser.setFullName(request.getFullName());
        newUser.setRole(userService.getRoleByName("ROLE_CUSTOMER")); // Default role

        // Save user
        userService.saveUser(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user or employee
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            UserDetails userDetails = authService.loadUserByUsername(loginRequest.getUsername());
            // Generate JWT token
            String jwt = jwtUtil.generateToken(userDetails);

            // Extract expiration date from the token
            Date expirationDate = jwtUtil.extractExpiration(jwt);

            // Save token to the database
            tokenService.saveToken(jwt, expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            // Return token in response
            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            tokenService.blacklistToken(token);
            return ResponseEntity.ok("Logged out successfully");
        }
        return ResponseEntity.badRequest().body("Invalid Authorization header");
    }

    @GetMapping("/getUserId/{username}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE','CUSTOMER')")
    public ResponseEntity<?> getUserId(@PathVariable String username) {
        Optional<String> userId = userService.getUserIdByUsername(username);
        if (userId.isPresent()) {
            return ResponseEntity.ok(Map.of("userType", "USER", "id", userId.get()));
        }

        Optional<String> employeeId = employeeService.getEmployeeIdByUsername(username);
        if (employeeId.isPresent()) {
            return ResponseEntity.ok(Map.of("userType", "EMPLOYEE", "id", employeeId.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALE', 'CUSTOMER')")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
