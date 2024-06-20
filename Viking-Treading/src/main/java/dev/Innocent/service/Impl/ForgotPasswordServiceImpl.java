package dev.Innocent.service.Impl;

import dev.Innocent.enums.VerificationType;
import dev.Innocent.model.ForgotPasswordToken;
import dev.Innocent.model.User;
import dev.Innocent.repository.ForgotPasswordRepository;
import dev.Innocent.service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    private final ForgotPasswordRepository forgotPasswordRepository;

    @Override
    public ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo) {
        ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
        forgotPasswordToken.setUser(user);
        forgotPasswordToken.setVerificationType(verificationType);
        forgotPasswordToken.setOtp(otp);
        forgotPasswordToken.setSendTo(sendTo);
        forgotPasswordToken.setId(id);
        return forgotPasswordRepository.save(forgotPasswordToken);
    }

    @Override
    public ForgotPasswordToken findById(String id) {
        Optional<ForgotPasswordToken> forgotPasswordToken = forgotPasswordRepository.findById(id);
        return forgotPasswordToken.orElse(null);
    }

    @Override
    public ForgotPasswordToken findByUser(Long userId) {
        return forgotPasswordRepository.findByUserId(userId);
    }

    @Override
    public void deleteToken(ForgotPasswordToken forgotPasswordToken) {
        forgotPasswordRepository.delete(forgotPasswordToken);
    }
}
