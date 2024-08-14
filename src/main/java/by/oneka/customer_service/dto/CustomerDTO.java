package by.oneka.customer_service.dto;

import java.util.UUID;

public record CustomerDTO(
        UUID id,
        String name,
        String email,
        char[] password
) {
}
