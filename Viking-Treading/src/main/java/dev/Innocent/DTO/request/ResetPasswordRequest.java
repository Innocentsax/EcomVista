package dev.Innocent.DTO.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String otp;
    private String password;
}
