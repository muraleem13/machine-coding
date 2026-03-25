package com.machinecoding.flipkartwallet.controller;

import com.machinecoding.flipkartwallet.dto.LoginRequest;
import com.machinecoding.flipkartwallet.dto.RegisterUserRequest;
import com.machinecoding.flipkartwallet.entity.User;
import com.machinecoding.flipkartwallet.repository.UserRepository;
import com.machinecoding.flipkartwallet.service.WalletService;
import com.machinecoding.flipkartwallet.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final WalletService walletService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterUserRequest request) {
        return walletService.register(request);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return Map.of("token", token);
    }
}