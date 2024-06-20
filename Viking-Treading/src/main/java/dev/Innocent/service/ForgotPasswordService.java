package dev.Innocent.service;

import dev.Innocent.enums.VerificationType;
import dev.Innocent.model.ForgotPasswordToken;
import dev.Innocent.model.User;

public interface ForgotPasswordService {
    ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo);
    ForgotPasswordToken findById(String id);
    ForgotPasswordToken findByUser(Long userId);
    void deleteToken(ForgotPasswordToken forgotPasswordToken);
}
