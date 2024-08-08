package com.agoracharis.customerservice.data;

import com.agoracharis.customerservice.model.Address;
import jakarta.validation.constraints.NotNull;

public record UpdateCustomerReq(@NotNull(message = "First name cannot be null or empty")
                                String firstName,
                                @NotNull(message = "Last name cannot be null or empty")
                                String lastName,
                                Address address) {
}
