package dev.Innocent.ecomvista.DTO.request;

import dev.Innocent.ecomvista.DTO.response.CustomerResponse;
import dev.Innocent.ecomvista.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
