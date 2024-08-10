package dev.Innocent.controller;

import dev.Innocent.DTO.request.CreateOrderRequest;
import dev.Innocent.model.Coin;
import dev.Innocent.model.Order;
import dev.Innocent.model.User;
import dev.Innocent.service.CoinService;
import dev.Innocent.service.OrderService;
import dev.Innocent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CoinService coinService;


    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody CreateOrderRequest createOrderRequest) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(createOrderRequest.getCoinId());
        Order order = orderService.processOrder(coin, createOrderRequest.getQuantity(), createOrderRequest.getOrderType(), user);
        return ResponseEntity.ok(order);
    }
}
