package dev.Innocent.service;

import dev.Innocent.model.TwoFactorOTP;
import dev.Innocent.model.User;

public interface TwoFactorOtpService {
    TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt);
    TwoFactorOTP findByUser(Long userId);
    TwoFactorOTP findById(String id);
    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp);
    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);
}
