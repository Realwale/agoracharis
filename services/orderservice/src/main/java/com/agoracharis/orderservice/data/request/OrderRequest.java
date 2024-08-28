package com.agoracharis.orderservice.data.request;

import com.agoracharis.orderservice.constant.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        String reference,
        @Positive(message = "Oder amount should be positive")
        BigDecimal totalAmount,
        @NotNull
        PaymentMethod paymentMethod,
        @NotBlank
        @NotEmpty
        @NotNull
        String customerId,
        @NotEmpty
        List<PurchaseRequest> products
) {
}
