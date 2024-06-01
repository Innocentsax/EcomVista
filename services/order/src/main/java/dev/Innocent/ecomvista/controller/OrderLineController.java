package dev.Innocent.ecomvista.controller;

import dev.Innocent.ecomvista.DTO.response.OrderLineResponse;
import dev.Innocent.ecomvista.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(orderLineService.findByOrderId(orderId));
    }
}
