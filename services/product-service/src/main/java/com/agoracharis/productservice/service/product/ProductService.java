package com.agoracharis.productservice.service.product;

import com.agoracharis.productservice.data.request.ProductPurchaseRequest;
import com.agoracharis.productservice.data.request.ProductRequest;
import com.agoracharis.productservice.data.response.ProductPurchaseResponse;
import com.agoracharis.productservice.data.response.ProductResponse;
import com.agoracharis.productservice.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    Integer createProduct(
            ProductRequest request
    );

    ProductResponse findById(Integer id);

    List<ProductResponse> findAll();

    @Transactional(rollbackFor = ResourceNotFoundException.class)
    List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    );

    Page<ProductResponse> searchForProduct(int pageSize, int pageNo, String searchPhrase);

    ProductResponse.ProductSkuResponse findBySku(String sku);
}
