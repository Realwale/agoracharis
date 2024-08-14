package com.agoracharis.productservice.service.category;

import com.agoracharis.productservice.data.request.CreateProductCategoryRequest;

public interface ProductCategoryService {

    Integer saveCategory(CreateProductCategoryRequest request);
}
