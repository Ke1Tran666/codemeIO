package com.poly.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import com.poly.bean.Category;
import com.poly.bean.Course;

public interface CourseService {

    List<Course> findAll();
    
    Course findById(Integer id);
    
    Course save(Course course);
    
    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<Course> findByTitle(String title);

    List<Course> findByTitleLike(String title);

    List<Course> findByCategory(Category category);

    List<Course> findByPriceLessThan(BigDecimal price);

    Integer countCoursesByCategory(Integer categoryId);
    
    List<Course> findByCategoryId(Integer categoryId);
    
    String saveUploadedFile(MultipartFile file);
}