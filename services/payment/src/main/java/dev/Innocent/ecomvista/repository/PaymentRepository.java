package dev.Innocent.ecomvista.repository;

import dev.Innocent.ecomvista.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
