package com.forestsoftware.sendmeerrandserverjava.service.category;

import com.forestsoftware.sendmeerrandserverjava.Request.CreateCategoryRequest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity createCategory(CreateCategoryRequest createCategoryRequest);

    ResponseEntity fetchAllCategories();

    ResponseEntity fetchAvailableCategories();
}
