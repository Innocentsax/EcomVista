package dev.Innocent.ecomvista.repository;

import dev.Innocent.ecomvista.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
