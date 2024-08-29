package com.agoracharis.notification.data;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        BigDecimal price,
        int quantity,
        String description
) {
}
