package dev.Innocent.model;

import dev.Innocent.enums.VerificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forgot_password_token")
public class ForgotPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String otp;
    private VerificationType verificationType;
    private String sendTo;

    @OneToOne
    private User user;
}
