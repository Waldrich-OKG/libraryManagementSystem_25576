package com.waldrich.libraryManagementSystem_25576.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.dto.AuthResponse;
import com.waldrich.libraryManagementSystem_25576.dto.LoginRequest;
import com.waldrich.libraryManagementSystem_25576.dto.LoginResponse;
import com.waldrich.libraryManagementSystem_25576.model.Users;
import com.waldrich.libraryManagementSystem_25576.repository.UsersRepository;

@Service
public class AuthService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;


    public LoginResponse initiateLogin(LoginRequest user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            String otp = otpService.generateOtp(user.getEmail());
            emailService.sendOtpEmail(user.getEmail(), otp);
            return new LoginResponse("OTP sent to your email!", true);

        }
        return new LoginResponse("Invalid credentials!", false);
    }

    public AuthResponse verifyOtp(String email, String otp) {
    if (otpService.validateOtp(email, otp)) {
        Users user = userRepository.findByEmail(email);
        if(user==null){
            throw new RuntimeException("User not found!");
        }

        String accessToken = jwtService.generateToken(user);

        String refreshToken = UUID.randomUUID().toString();

        user.setRefreshToken(refreshToken);
        user.setTokenExpiry(LocalDateTime.now().plusDays(7));
        userRepository.save(user);

        return new AuthResponse(accessToken, refreshToken);
    }
    throw new RuntimeException("Invalid or expired OTP!");
}
    
}
