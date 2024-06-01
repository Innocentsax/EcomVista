package dev.Innocent.ecomvista.service;

import dev.Innocent.ecomvista.DTO.request.OrderLineRequest;
import dev.Innocent.ecomvista.DTO.response.OrderLineResponse;
import dev.Innocent.ecomvista.model.Order;
import dev.Innocent.ecomvista.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
