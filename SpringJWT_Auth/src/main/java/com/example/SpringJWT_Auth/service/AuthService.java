package com.example.SpringJWT_Auth.service;

import com.example.SpringJWT_Auth.dto.AuthRequest;
import com.example.SpringJWT_Auth.entity.User;
import com.example.SpringJWT_Auth.repository.UserRepository;
import com.example.SpringJWT_Auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String signup(AuthRequest request) {
        // ✅ STEP 1: Check duplicate username
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // ✅ STEP 2: Create user
        User user = new User();
        user.setUsername(request.getUsername());

        // ✅ STEP 3: Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // ✅ STEP 4: Assign role
        user.setRole("ROLE_" + request.getRole());
        // ✅ STEP 5: Save user
        userRepository.save(user);
        return "Signup successful";
    }

    public String login(AuthRequest request) {
        // ✅ STEP 1: Fetch user
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        // ✅ STEP 2: Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ STEP 3: Generate JWT token
        return jwtUtil.generateToken(user);
    }
}

