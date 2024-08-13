package dev.Innocent.controller;

import dev.Innocent.model.Coin;
import dev.Innocent.model.User;
import dev.Innocent.model.WatchList;
import dev.Innocent.service.CoinService;
import dev.Innocent.service.UserService;
import dev.Innocent.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {
    private final WatchlistService watchlistService;
    private final UserService userService;
    private final CoinService coinService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserWatchlist(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        WatchList watchList = watchlistService.findUserWatchlist(user.getId());
        return new ResponseEntity<>(watchList, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> createWatchlist(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        WatchList watchList = watchlistService.createWatchlist(user);
        return new ResponseEntity<>(watchList, HttpStatus.CREATED);
    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<?> getWatchlistById(@PathVariable Long watchlistId) throws Exception {
        WatchList watchList = watchlistService.findById(watchlistId);
        return new ResponseEntity<>(watchList, HttpStatus.OK);
    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<?> addCoinToWatchlist(
            @RequestHeader("Authorization") String jwt, @PathVariable String coinId) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(coinId);
        Coin addedCoin = watchlistService.addItemToWatchlist(user, coin);
        return new ResponseEntity<>(addedCoin, HttpStatus.OK);
    }
}
