package dev.Innocent.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.Innocent.model.Coin;
import dev.Innocent.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coin")
public class CoinController {
    private final CoinService coinService;
    private ObjectMapper objectMapper;

    @GetMapping("/list")
    ResponseEntity<List<Coin>> getCoinList(@RequestParam("page") int page) throws Exception {
        List<Coin> coinList = coinService.getCoinList(page);
        return new ResponseEntity<>(coinList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{coinId}/chart")
    ResponseEntity<JsonNode> getMarketChart(
            @RequestParam("days") int days,
            @PathVariable String coinId) throws Exception {
        String marketChart = coinService.getMarketChart(coinId, days);
        JsonNode jsonNode = objectMapper.readTree(marketChart);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    ResponseEntity<JsonNode> searchCoin(@RequestParam("q") String keyword) throws Exception {
        String searchResult = coinService.searchCoin(keyword);
        JsonNode jsonNode = objectMapper.readTree(searchResult);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/top50")
    ResponseEntity<JsonNode> getTop50CoinByMarketCapBank() throws Exception {
        String top50Coin = coinService.getTop50CoinsByMarketCapRank();
        JsonNode jsonNode = objectMapper.readTree(top50Coin);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/treading")
    ResponseEntity<JsonNode> getTreadingCoin() throws Exception {
        String treadingCoin = coinService.getTreadingCoins();
        JsonNode jsonNode = objectMapper.readTree(treadingCoin);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }

    @GetMapping("/details/{coinId}")
    ResponseEntity<JsonNode> getCoinDetails(@PathVariable String coinId) throws Exception {
        String coinDetails = coinService.getCoinDetails(coinId);
        JsonNode jsonNode = objectMapper.readTree(coinDetails);
        return new ResponseEntity<>(jsonNode, HttpStatus.ACCEPTED);
    }
}
