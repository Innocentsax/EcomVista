package dev.Innocent.model;

import dev.Innocent.enums.VerificationType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
