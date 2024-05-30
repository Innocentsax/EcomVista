package dev.innocent.ecomvista.DTO.response;

import dev.innocent.ecomvista.customer.Address;

public record CustomerResponse(
        String id,

        String firstname,

        String lastname,

        String email,

        Address address
) {
}
