package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.LoginRequest;
import com.Rachit.mental_health.dto.RegisterRequest;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.repository.UserRepository;
import com.Rachit.mental_health.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByUserId(request.getUserId()).isPresent()) {
            return ResponseEntity.badRequest().body("ID already registered.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER"); // Default role

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        Optional<User> user = userRepository.findByUserId(request.getUserId());
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        String token = jwtUtil.generateToken(user.get().getUsername(), user.get().getUserId());
        return ResponseEntity.ok().body(token);
    }
}