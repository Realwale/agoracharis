package com.agoracharis.orderservice.data.response;

import java.math.BigDecimal;

public record PurchaseResp(
        Integer productId,
        String name,
        BigDecimal price,
        int quantity,
        String description
) {
}
