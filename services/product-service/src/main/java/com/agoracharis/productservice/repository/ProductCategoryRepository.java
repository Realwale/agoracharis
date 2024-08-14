package com.agoracharis.productservice.repository;

import com.agoracharis.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
