package dev.innocent.ecomvista.DTO.request;

import dev.innocent.ecomvista.customer.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,

        @NotNull(message = "Firstname is required")
        String firstname,

        @NotNull(message = "Lastname is required")
        String lastname,

        @Email(message = "Email is not a valid email address")
        @NotNull(message = "Email is required")
        String email,

        Address address
) {
}
