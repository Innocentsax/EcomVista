package dev.Innocent.ecomvista.repository;

import dev.Innocent.ecomvista.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
