package dev.Innocent.DTO.request;

import dev.Innocent.enums.VerificationType;
import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {
    private String sendTo;
    private VerificationType verificationType;
}
