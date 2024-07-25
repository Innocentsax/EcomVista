package dev.Innocent.auth.repository;

import dev.Innocent.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
