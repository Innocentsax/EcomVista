package dev.Innocent.ecomvista.DTO.request;

import dev.Innocent.ecomvista.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "Amount should be greater than 0")
        BigDecimal amount,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer ID is required")
        @NotBlank(message = "Customer ID is required")
        @NotEmpty(message = "Customer ID is required")
        String customerId,

        @NotEmpty(message = "Order should have at least one product")
        List<PurchaseRequest> products
) {
}
