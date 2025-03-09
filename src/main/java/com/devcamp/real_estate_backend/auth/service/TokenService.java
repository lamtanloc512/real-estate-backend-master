package com.devcamp.real_estate_backend.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.real_estate_backend.auth.entity.Token;
import com.devcamp.real_estate_backend.auth.repository.TokenRepository;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public void saveToken(String token, LocalDateTime expireDate) {
        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setExpireDate(expireDate);
        tokenRepository.save(newToken);
    }

    public boolean isTokenBlacklisted(String token) {
        Optional<Token> existingToken = tokenRepository.findByToken(token);
        return existingToken.map(Token::getIsBlacklisted).orElse(false);
    }

    public void blacklistToken(String token) {
        tokenRepository.findByToken(token).ifPresent(existingToken -> {
            existingToken.setIsBlacklisted(true);
            tokenRepository.save(existingToken);
        });
    }
}
