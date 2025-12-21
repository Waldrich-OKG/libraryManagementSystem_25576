package com.waldrich.libraryManagementSystem_25576.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waldrich.libraryManagementSystem_25576.dto.AuthResponse;
import com.waldrich.libraryManagementSystem_25576.dto.LoginRequest;
import com.waldrich.libraryManagementSystem_25576.dto.LoginResponse;
import com.waldrich.libraryManagementSystem_25576.model.Users;
import com.waldrich.libraryManagementSystem_25576.repository.UsersRepository;
import com.waldrich.libraryManagementSystem_25576.service.AuthService;
import com.waldrich.libraryManagementSystem_25576.service.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest user) {
        LoginResponse response = authService.initiateLogin(user);
        if(response.isSuccess()) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        AuthResponse response = authService.verifyOtp(email, otp);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        Users user = userRepository.findByRefreshToken(refreshToken);
        if(user == null) {
            throw new RuntimeException("Invalid refresh token.");
        }

        if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired. Please login again.");
        }

        
        String newAccessToken = jwtService.generateToken(user);

        String newRefreshToken = UUID.randomUUID().toString();
        user.setRefreshToken(newRefreshToken);
        user.setTokenExpiry(LocalDateTime.now().plusDays(7));
        userRepository.save(user);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
    }

}
