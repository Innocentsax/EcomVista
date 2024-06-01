package dev.Innocent.ecomvista.handler;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) {

}