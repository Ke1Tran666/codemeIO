package com.poly.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.bean.Category;
import com.poly.bean.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitle(String title);

    List<Course> findByTitleContaining(String title); // Tìm kiếm theo tiêu đề

    List<Course> findByCategory(Category category); // Tìm kiếm theo danh mục

    List<Course> findByPriceLessThan(BigDecimal price); // Tìm kiếm theo giá

    Long countByCategory_CategoryId(Long categoryId); // Đếm số khóa học theo danh mục
}