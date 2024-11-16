package com.poly.service;


import java.util.List;

import com.poly.bean.Category;
import com.poly.bean.Course;

import java.math.BigDecimal;

public interface CourseService {

    List<Course> findAll();
    
    Course findById(Long id);
    
    Course save(Course course);
    
    void deleteById(Long id);

    boolean existsById(Long id);

    List<Course> findByTitle(String title);

    List<Course> findByTitleLike(String title);

    List<Course> findByCategory(Category category);

    List<Course> findByPriceLessThan(BigDecimal price);

    Long countCoursesByCategory(Long categoryId);
}