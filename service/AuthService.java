package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.LoginRequest;
import com.Rachit.mental_health.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> login(LoginRequest request);
}
