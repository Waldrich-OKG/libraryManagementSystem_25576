package com.waldrich.libraryManagementSystem_25576.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.model.Users;
import com.waldrich.libraryManagementSystem_25576.repository.UsersRepository;

@Service
public class OtpService {

    @Autowired
    private UsersRepository userRepository;

    private final Random random = new Random();

    public String generateOtp(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
            

        String otp = String.format("%06d", random.nextInt(999999));
        user.setOtpCode(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        user.setOtpVerified(false);
        user.setOtpAttempts(0);

        userRepository.save(user);
        return otp;
    }


    public boolean validateOtp(String email, String otp) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            user.setOtpCode(null);
            userRepository.save(user);
            return false;
        }

        if (user.getOtpAttempts() >= 3) {
            return false; // too many attempts
        }

        boolean isValid = otp.equals(user.getOtpCode());
        user.setOtpAttempts(user.getOtpAttempts() + 1);

        if (isValid) {
            user.setOtpVerified(true);
            user.setOtpCode(null); // clear OTP after success
        }

        userRepository.save(user);
        return isValid;
    }

}
