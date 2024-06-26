package dev.Innocent.service;

import dev.Innocent.enums.OrderType;
import dev.Innocent.model.Coin;
import dev.Innocent.model.Order;
import dev.Innocent.model.OrderItem;
import dev.Innocent.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, OrderItem orderItem, OrderType orderType);
    Order getOrderById(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol);
    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;
}
