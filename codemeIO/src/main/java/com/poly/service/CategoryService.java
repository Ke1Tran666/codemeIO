package com.poly.service;

import java.util.List;

import com.poly.bean.Category;

public interface CategoryService {

    List<Category> findAll();
    
    Category findById(Long id);
    
    Category save(Category category);
    
    void deleteById(Long id);

    boolean existsById(Long id);

    List<Category> findByCategoryNameLike(String categoryName);
}