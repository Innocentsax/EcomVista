package dev.Innocent.model;

import dev.Innocent.enums.WithdrawalStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private WithdrawalStatus status;
    private Long amount;

    @ManyToOne
    private User user;
    private LocalDateTime date = LocalDateTime.now();
}
