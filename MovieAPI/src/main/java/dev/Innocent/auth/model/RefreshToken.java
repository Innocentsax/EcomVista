package dev.Innocent.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "Refresh token is mandatory")
    private String refreshToken;

    @Column(nullable = false)
    private Instant expirationTime;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
