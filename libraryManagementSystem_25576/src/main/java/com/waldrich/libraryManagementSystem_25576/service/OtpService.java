package com.waldrich.libraryManagementSystem_25576.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
    
    private final Map<String, OtpData> otpStorage = new HashMap<>();

    private static class OtpData {
        String otp;
        LocalDateTime expiryTime;
        OtpData(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }

    public String generateOtp(String username) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStorage.put(username, new OtpData(otp, LocalDateTime.now().plusMinutes(5)));
        return otp;
    }

    public boolean validateOtp(String username, String otp) {
        if (!otpStorage.containsKey(username)) return false;
        OtpData data = otpStorage.get(username);

        if (data.expiryTime.isBefore(LocalDateTime.now())) {
            otpStorage.remove(username);
            return false;
        }

        boolean isValid = data.otp.equals(otp);
        if (isValid) otpStorage.remove(username);
        return isValid;
    }
}
