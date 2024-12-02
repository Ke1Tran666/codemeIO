package com.poly.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Category;
import com.poly.bean.Course;
import com.poly.repository.CategoryRepository;
import com.poly.repository.CourseRepository;
import com.poly.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired 
    private CategoryRepository categoryRepo;
    
    @Autowired 
    private CourseRepository courseRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return categoryRepo.existsById(id);
    }

    @Override
    public List<Category> findByCategoryNameLike(String categoryName) {
        return categoryRepo.findByCategoryNameContaining(categoryName);
    }
    
    @Override
    public List<Course> findCoursesByCategoryId(Integer categoryId) {
        // Giả sử bạn có một CourseRepository để thực hiện truy vấn
        return courseRepo.findByCategory_CategoryId(categoryId); // Thêm logic truy vấn tại đây
    }
}