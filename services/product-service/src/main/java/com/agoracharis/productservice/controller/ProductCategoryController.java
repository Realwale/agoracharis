package com.agoracharis.productservice.controller;


import com.agoracharis.productservice.data.request.CreateProductCategoryRequest;
import com.agoracharis.productservice.service.category.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    @PostMapping
    public ResponseEntity<Integer> addCategory(@RequestBody CreateProductCategoryRequest request){
        return new ResponseEntity<>(categoryService.saveCategory(request), HttpStatus.CREATED);
    }
}
