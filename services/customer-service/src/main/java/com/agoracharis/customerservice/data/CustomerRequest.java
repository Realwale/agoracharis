package com.agoracharis.customerservice.data;

import com.agoracharis.customerservice.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(@NotNull(message = "First name cannot be null or empty")
                              String firstName,
                              @NotNull(message = "Last name cannot be null or empty")
                              String lastName,
                              @NotNull(message = "Email cannot be null or empty")
                              @Email(message = "Invalid email")
                              String email,
                              Address address
                              ) {
}
