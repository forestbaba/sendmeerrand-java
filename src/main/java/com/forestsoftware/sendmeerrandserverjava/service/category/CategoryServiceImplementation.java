package com.forestsoftware.sendmeerrandserverjava.service.category;

import com.forestsoftware.sendmeerrandserverjava.Request.CreateCategoryRequest;
import com.forestsoftware.sendmeerrandserverjava.Response.ApiResponse;
import com.forestsoftware.sendmeerrandserverjava.models.Category;
import com.forestsoftware.sendmeerrandserverjava.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImplementation  implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = categoryRepository.findByTitle(createCategoryRequest.getTitle());
        if(category != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false,String.format("Category with name %s already exist", createCategoryRequest.getTitle()), null));
        }
        Category newCategory = new Category();
        newCategory.setTitle(createCategoryRequest.getTitle());
        newCategory.setImage(createCategoryRequest.getImage());
        newCategory.setAvailable(createCategoryRequest.isAvailable());
        categoryRepository.save(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Category created",newCategory));
    }

    @Override
    public ResponseEntity fetchAllCategories() {
        return ResponseEntity.ok(new ApiResponse(true,"Categories fetched",categoryRepository.findAll()));
    }

    @Override
    public ResponseEntity fetchAvailableCategories() {
        return ResponseEntity.ok(new ApiResponse(true, "Available fetched", categoryRepository.getAllByAvailable(true)));
    }
}
