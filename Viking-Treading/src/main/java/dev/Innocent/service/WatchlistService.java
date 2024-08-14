package dev.Innocent.service;

import dev.Innocent.model.Coin;
import dev.Innocent.model.User;
import dev.Innocent.model.WatchList;

public interface WatchlistService {
    WatchList findUserWatchlist(Long userId) throws Exception;
    WatchList createWatchlist(User user) throws Exception;
    WatchList findById(Long id) throws Exception;

    Coin addItemToWatchlist(User user, Coin coin) throws Exception;
}
