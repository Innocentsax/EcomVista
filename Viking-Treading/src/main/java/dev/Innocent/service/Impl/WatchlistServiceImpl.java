package dev.Innocent.service.Impl;

import dev.Innocent.model.Coin;
import dev.Innocent.model.User;
import dev.Innocent.model.WatchList;
import dev.Innocent.repository.WatchlistRepository;
import dev.Innocent.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {
    private final WatchlistRepository watchlistRepository;

    @Override
    public WatchList findUserWatchlist(Long userId) throws Exception {
        WatchList watchList = watchlistRepository.findByUserId(userId);
        if (watchList == null) {
            throw new Exception("Watchlist not found");
        }
        return watchList;
    }

    @Override
    public WatchList createWatchlist(User user) throws Exception {
        WatchList watchList = new WatchList();
        watchList.setUser(user);
        return watchlistRepository.save(watchList);
    }

    @Override
    public WatchList findById(Long id) throws Exception {
        return watchlistRepository.findById(id).orElseThrow(() -> new Exception("Watchlist not found"));
    }

    @Override
    public Coin addItemToWatchlist(User user, Coin coin) throws Exception {
        WatchList watchList = findUserWatchlist(user.getId());
        if(watchList.getCoins().contains(coin)) {
            watchList.getCoins().remove(coin);
        }else{
            watchList.getCoins().add(coin);
        }
        watchlistRepository.save(watchList);
        return coin;
    }
}
