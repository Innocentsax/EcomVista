package dev.Innocent.ecomvista.repository;

import dev.Innocent.ecomvista.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> products);
}
