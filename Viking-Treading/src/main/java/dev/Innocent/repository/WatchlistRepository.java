package dev.Innocent.repository;

import dev.Innocent.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<WatchList, Long> {
    WatchList findByUserId(Long userId);
}
