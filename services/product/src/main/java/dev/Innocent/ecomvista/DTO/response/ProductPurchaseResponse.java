package dev.Innocent.ecomvista.DTO.response;

public record ProductPurchaseResponse(
        Integer productId,
        Double quantity,
        Double price) {
}
