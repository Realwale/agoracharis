package com.agoracharis.payment.data.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "First name cannot be null or empty")
        String firstName,
        @NotNull(message = "Last name cannot be null or empty")
        String lastName,
        @NotNull(message = "Email cannot be null or empty")
        @Email(message = "Enter valid emaIL")
        String email
) {
}
