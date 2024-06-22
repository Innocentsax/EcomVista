package dev.Innocent.model;

import dev.Innocent.enums.VerificationType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otp;
    private String email;
    private String mobile;
    private VerificationType verificationType;

    @OneToOne
    private User user;
}
