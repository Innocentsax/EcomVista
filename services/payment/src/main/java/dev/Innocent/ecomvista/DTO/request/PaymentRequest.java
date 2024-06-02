package dev.Innocent.ecomvista.DTO.request;

import dev.Innocent.ecomvista.enums.PaymentMethod;
import dev.Innocent.ecomvista.model.Customer;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
