package com.agoracharis.productservice.data.response;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}