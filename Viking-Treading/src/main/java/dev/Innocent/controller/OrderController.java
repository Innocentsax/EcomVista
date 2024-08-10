package dev.Innocent.controller;

import dev.Innocent.DTO.request.CreateOrderRequest;
import dev.Innocent.enums.OrderType;
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

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersForUser(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(required = false) OrderType order_type,
            @RequestParam(required = false) String asset_symbol
    ) throws Exception {
        Long userId = userService.findUserProfileByJwt(jwt).getId();
        List<Order> orders = orderService.getAllOrdersOfUser(userId, order_type, asset_symbol);
        return ResponseEntity.ok(orders);
    }

}
