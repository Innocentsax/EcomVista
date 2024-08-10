package dev.Innocent.DTO.request;

import dev.Innocent.enums.OrderType;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}
