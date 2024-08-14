package com.agoracharis.productservice.repository;


import com.agoracharis.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);

    Page<Product> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(Pageable responsePage,
                                                                                             String description,
                                                                                             String searchPhrase);

    Optional<Product> findBySku(String sku);
}
