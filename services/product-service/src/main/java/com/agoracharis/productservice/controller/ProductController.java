package com.agoracharis.productservice.controller;


import com.agoracharis.productservice.data.request.ProductRequest;
import com.agoracharis.productservice.data.response.ProductResponse;
import com.agoracharis.productservice.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> saveProduct(@RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Integer productId){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> searchProduct(@RequestParam("page") int pageNo,
                                                                   @RequestParam("size") int pageSize,
                                                                   @RequestParam("search") String query){
        return ResponseEntity.ok(productService.searchForProduct(pageNo,pageSize,query));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponse.ProductSkuResponse> findProductById(@PathVariable String sku){
        return ResponseEntity.ok(productService.findBySku(sku));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchProducts(){
        return ResponseEntity.ok(productService.findAll());
    }
}
