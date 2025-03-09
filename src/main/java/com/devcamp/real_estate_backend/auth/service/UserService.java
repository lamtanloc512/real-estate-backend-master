package com.devcamp.real_estate_backend.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.auth.entity.Role;
import com.devcamp.real_estate_backend.auth.entity.User;
import com.devcamp.real_estate_backend.auth.repository.RoleRepository;
import com.devcamp.real_estate_backend.auth.repository.UserRepository;
import com.devcamp.real_estate_backend.auth.security.UnifiedPrincipal;
import com.devcamp.real_estate_backend.model.ConstructionContractor;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public String generateUserId() {
        long count = userRepository.count() + 1; // Get total users and increment
        return "user" + count;
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updatePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UnifiedPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getRole().getName());
    }

    public Optional<String> getUserIdByUsername(String username) {
        return userRepository.findByUsername(username).map(User::getId);
    }

    // Get a contractor by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
