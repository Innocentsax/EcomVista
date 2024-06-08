package dev.Innocent.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

        @Id
        @GeneratedValue
        private Integer id;

        private String token;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;
        private LocalDateTime validatedAt;

        @ManyToOne
        @JoinColumn(nullable = false, name = "user_id")
        private User user;

}
