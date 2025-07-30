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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private EmailClientService emailClientService;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        String role = request.getRole();
        user.setRole(role != null && (role.equals("USER") || role.equals("ADMIN"))? role : "USER");
        user.setCreateDate(LocalDate.from(LocalDateTime.now()));
        userRepository.save(user);
        emailClientService.sendEmail(user.getEmail(), "Welcome!", "Thanks for registering, " + user.getUsername() + "! Your account has been created successfully.");
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

        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) return ResponseEntity.status(404).body("User not found");

        User user = userOpt.get();
        String accessToken = jwtUtil.generateToken(user.getEmail(), user.getUserId(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail(), user.getUserId());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return ResponseEntity.ok(tokens);


    }

}