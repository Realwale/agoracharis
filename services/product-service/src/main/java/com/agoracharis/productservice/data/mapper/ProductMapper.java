package com.agoracharis.productservice.data.mapper;

import com.agoracharis.productservice.data.request.ProductRequest;
import com.agoracharis.productservice.data.response.ProductPurchaseResponse;
import com.agoracharis.productservice.data.response.ProductResponse;
import com.agoracharis.productservice.model.Category;
import com.agoracharis.productservice.model.Product;
import com.agoracharis.productservice.utils.SkuGenerator;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .sku(SkuGenerator.generateSku())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toproductPurchaseResponse(Product product, int quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }


    public ProductResponse.ProductSkuResponse toProductSkuResponse(Product product) {
        return new ProductResponse.ProductSkuResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }
}