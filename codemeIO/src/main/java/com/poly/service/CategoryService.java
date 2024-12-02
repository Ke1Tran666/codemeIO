package com.poly.service;

import java.util.List;

import com.poly.bean.Category;
import com.poly.bean.Course;

public interface CategoryService {

    List<Category> findAll();
    
    Category findById(Integer id);
    
    Category save(Category category);
    
    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<Category> findByCategoryNameLike(String categoryName);
    
    List<Course> findCoursesByCategoryId(Integer categoryId);
}