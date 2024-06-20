package dev.Innocent.controller;

import dev.Innocent.DTO.request.ForgotPasswordTokenRequest;
import dev.Innocent.DTO.request.ResetPasswordRequest;
import dev.Innocent.DTO.response.ApiResponse;
import dev.Innocent.DTO.response.AuthResponse;
import dev.Innocent.enums.VerificationType;
import dev.Innocent.model.ForgotPasswordToken;
import dev.Innocent.model.User;
import dev.Innocent.model.VerificationCode;
import dev.Innocent.service.Impl.EmailServiceImpl;
import dev.Innocent.service.Impl.ForgotPasswordServiceImpl;
import dev.Innocent.service.UserService;
import dev.Innocent.service.VerificationCodeService;
import dev.Innocent.utils.OtpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final VerificationCodeService verificationCodeService;
    private final ForgotPasswordServiceImpl forgotPasswordService;

    @GetMapping("/user/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/users/enable-two-factor/verify-otp/{otp}")
    public ResponseEntity<User> enableTwoFactorAuthentication(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("otp") String otp) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());

        String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL) ?
                verificationCode.getEmail() : verificationCode.getMobile();
        boolean isVerified = verificationCode.getOtp().equals(otp);

        if(isVerified){
            User updatedUser = userService.enableTwoFactorAuthentication(
                    verificationCode.getVerificationType(), sendTo, user);
            verificationCodeService.deleteVerificationCodeById(verificationCode);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        throw new Exception("Invalid OTP");
    }

    @PostMapping("/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOtp(
            @RequestHeader("Authorization") String jwt,
            @PathVariable VerificationType verificationType) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());
        if(verificationCode == null){
            verificationCode = verificationCodeService.sendVerificationCode(user, verificationType);
        }
        if(verificationType.equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());
        }

        return new ResponseEntity<>("Verification code sent", HttpStatus.OK);
    }

    @PostMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse> sendForgotPasswordOtp(@RequestBody ForgotPasswordTokenRequest request) throws Exception {
        User user = userService.findUserByEmail(request.getSendTo());
        String otp = OtpUtils.generateOtp();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();

        ForgotPasswordToken forgotPasswordToken = forgotPasswordService.findByUser(user.getId());
        if(forgotPasswordToken == null){
            forgotPasswordToken = forgotPasswordService.createToken(
                    user, id, otp, request.getVerificationType(), request.getSendTo());
        }
        if(request.getVerificationType().equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(user.getEmail(), forgotPasswordToken.getOtp());
        }
        AuthResponse authResponse = new AuthResponse();
        authResponse.setSession(forgotPasswordToken.getId());
        authResponse.setMessage("Password reset otp sent successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PatchMapping("/auth/users/reset-password/verify-otp")
    public ResponseEntity<ApiResponse> enableTwoFactorAuthentication(
            @RequestHeader("Authorization") String jwt,
            @RequestBody ResetPasswordRequest request,
            @RequestParam String id) throws Exception {

        ForgotPasswordToken forgotPasswordToken = forgotPasswordService.findById(id);
        boolean isVerified = forgotPasswordToken.getOtp().equals(request.getOtp());

        if(isVerified){
            userService.updatePassword(forgotPasswordToken.getUser(), request.getPassword());
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Password reset successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        throw new Exception("Invalid OTP");
    }
}
