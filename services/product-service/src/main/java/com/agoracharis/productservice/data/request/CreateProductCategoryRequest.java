package com.agoracharis.productservice.data.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;


public record CreateProductCategoryRequest (

    @UniqueElements(message = "Product category with this name already exists")
    @NotNull(message = "Product description is required")
    String name,
    String description
){
}
