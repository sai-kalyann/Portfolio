package com.portfolio.user_service.controller;

import com.portfolio.user_service.dto.UserRequestDTO;
import com.portfolio.user_service.model.User;
import com.portfolio.user_service.security.JwtUtil;
import com.portfolio.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserRequestDTO dto) {
        if (userService.getUserByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }
        User user = com.portfolio.user_service.dto.UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        User saved = userService.createUser(user);
        String token = jwtUtil.generateToken(saved.getEmail(), java.util.List.of(saved.getRole()));
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", com.portfolio.user_service.dto.UserMapper.toResponseDTO(saved));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        return userService.getUserByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getEmail(), java.util.List.of(user.getRole()));
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token);
                    response.put("user", com.portfolio.user_service.dto.UserMapper.toResponseDTO(user));
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }
}

