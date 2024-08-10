package dev.Innocent.controller;

import dev.Innocent.service.CoinService;
import dev.Innocent.service.OrderService;
import dev.Innocent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CoinService coinService;


}
