package dev.Innocent.ecomvista.repository;

import dev.Innocent.ecomvista.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
