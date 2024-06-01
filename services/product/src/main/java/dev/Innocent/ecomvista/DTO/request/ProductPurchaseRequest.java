package dev.Innocent.ecomvista.DTO.request;

public record ProductPurchaseRequest(
        Integer productId,
        Double quantity
) {
}
