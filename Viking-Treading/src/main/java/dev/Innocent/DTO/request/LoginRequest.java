package dev.Innocent.DTO.request;

import dev.Innocent.model.TwoFactorAuth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
    private TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
}