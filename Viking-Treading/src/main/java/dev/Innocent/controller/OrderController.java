package dev.Innocent.controller;

import dev.Innocent.DTO.request.CreateOrderRequest;
import dev.Innocent.model.Coin;
import dev.Innocent.model.Order;
import dev.Innocent.model.User;
import dev.Innocent.service.CoinService;
import dev.Innocent.service.OrderService;
import dev.Innocent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestHeader("Authorization") String jwt,
                                              @PathVariable Long orderId) throws Exception {
        if(jwt == null){
            throw new Exception("Token missing");
        }
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.getOrderById(orderId);
        if(order.getUser().getId().equals(user.getId())){
            return ResponseEntity.ok(order);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


}
