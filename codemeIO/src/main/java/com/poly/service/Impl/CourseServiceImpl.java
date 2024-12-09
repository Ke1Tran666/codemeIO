package com.poly.service.Impl;

import java.util.List;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.bean.Category;
import com.poly.bean.Course;
import com.poly.repository.CourseRepository;
import com.poly.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired 
    private CourseRepository courseRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course findById(Integer id) {
        return courseRepo.findById(id).orElse(null); // Sử dụng Optional
    }

    @Override
    public Course save(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public void deleteById(Integer id) {
        courseRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return courseRepo.existsById(id);
    }

    @Override
    public List<Course> findByTitle(String title) {
        return courseRepo.findByTitle(title);
    }

    @Override
    public List<Course> findByTitleLike(String title) {
        return courseRepo.findByTitleContaining(title); // Sử dụng phương thức phù hợp
    }

    @Override
    public List<Course> findByCategory(Category category) {
        return courseRepo.findByCategory(category);
    }

    @Override
    public List<Course> findByPriceLessThan(BigDecimal price) {
        return courseRepo.findByPriceLessThan(price);
    }

    @Override
    public Integer countCoursesByCategory(Integer categoryId) {
        return courseRepo.countByCategory_CategoryId(categoryId); // Đảm bảo phương thức này tồn tại trong repository
    }
    
    @Override
    public List<Course> findByCategoryId(Integer categoryId) {
        return courseRepo.findByCategory_CategoryId(categoryId); // Sử dụng phương thức từ repository
    }
}