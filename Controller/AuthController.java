package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.LoginRequest;
import com.Rachit.mental_health.dto.RegisterRequest;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.service.AuthService;
import com.Rachit.mental_health.repository.UserRepository;
import com.Rachit.mental_health.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body("Invalid or expired refresh token");
        }

        String email = jwtUtil.extractUsername(refreshToken);
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(404).body("User not found");

        User user = userOpt.get();
        String newAccessToken = jwtUtil.generateToken(user.getEmail(), user.getUserId(), user.getRole());

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", newAccessToken);
        response.put("refreshToken", refreshToken); // optionally rotate

        return ResponseEntity.ok(response);
    }

}

