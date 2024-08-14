package com.agoracharis.productservice.data.response;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        int availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {

    public record ProductSkuResponse(
            Integer id,
            String name,
            String description,
            String sku,
            int availableQuantity,
            BigDecimal price,
            Integer categoryId,
            String categoryName,
            String categoryDescription
    ) {
    }
}
