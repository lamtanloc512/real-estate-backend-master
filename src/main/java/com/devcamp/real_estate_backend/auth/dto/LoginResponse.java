package com.devcamp.real_estate_backend.auth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private Collection<? extends GrantedAuthority> roles;
}