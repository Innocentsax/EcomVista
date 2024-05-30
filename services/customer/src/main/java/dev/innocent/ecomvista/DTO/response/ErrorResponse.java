package dev.innocent.ecomvista.DTO.response;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
