package com.forestsoftware.sendmeerrandserverjava.repository;


import com.forestsoftware.sendmeerrandserverjava.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String name);
    List<Category>getAllByAvailable(boolean available);
}
