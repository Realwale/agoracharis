package com.agoracharis.productservice.service.product;


import com.agoracharis.productservice.data.mapper.ProductMapper;
import com.agoracharis.productservice.data.request.ProductPurchaseRequest;
import com.agoracharis.productservice.data.request.ProductRequest;
import com.agoracharis.productservice.data.response.ProductPurchaseResponse;
import com.agoracharis.productservice.data.response.ProductResponse;
import com.agoracharis.productservice.exception.InvalidArgumentException;
import com.agoracharis.productservice.exception.ResourceNotFoundException;
import com.agoracharis.productservice.model.Product;
import com.agoracharis.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public Integer createProduct(
            ProductRequest request
    ) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    @Override
    public ProductResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID:: " + id));
    }

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = ResourceNotFoundException.class)
    @Override
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    ) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ResourceNotFoundException("One or more products does not exist");
        }
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new InvalidArgumentException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    @Override
    public Page<ProductResponse> searchForProduct(int pageSize, int pageNo, String searchPhrase) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Product> productResponses = repository
                .findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(pageable, searchPhrase, searchPhrase);

        List<ProductResponse> productResponseList =  productResponses.getContent()
                .stream()
                .map(mapper::toProductResponse)
                .toList();

        return new PageImpl<>(productResponseList,pageable,productResponses.getTotalElements());
    }

    @Override
    public ProductResponse.ProductSkuResponse findBySku(String sku) {
        return repository.findBySku(sku)
                .map(mapper::toProductSkuResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with Sku-Code:: " + sku));
    }
}
