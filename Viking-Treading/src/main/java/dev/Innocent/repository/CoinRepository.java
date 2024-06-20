package dev.Innocent.repository;

import dev.Innocent.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, String>{
}
