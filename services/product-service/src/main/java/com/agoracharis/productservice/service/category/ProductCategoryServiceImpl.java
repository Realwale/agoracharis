package com.agoracharis.productservice.service.category;


import com.agoracharis.productservice.data.request.CreateProductCategoryRequest;
import com.agoracharis.productservice.model.Category;
import com.agoracharis.productservice.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService{

    private final ProductCategoryRepository categoryRepository;


    @Override
    public Integer saveCategory(CreateProductCategoryRequest request){

        var category = categoryRepository.findByName(request.name());
        if (category==null){
            category = new Category();
            category.setName(request.name());
            category.setDescription(request.description());
            categoryRepository.save(category);
        }
        return category.getId();
    }

   // public CategoryResponse fetchCategory
}
