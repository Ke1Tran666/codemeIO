package com.poly.controller.rest;

import com.poly.bean.Category;
import com.poly.bean.Course;
import com.poly.service.CategoryService;
import com.poly.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(201).body(savedCategory);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        // Kiểm tra xem danh mục có tồn tại không
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật danh mục
        category.setCategoryId(id); // Gán ID cho thực thể để cập nhật
        Category updatedCategory = categoryService.save(category);
        return ResponseEntity.ok(updatedCategory);
    }
    
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByCategoryId(@PathVariable Integer id) {
        List<Course> courses = categoryService.findCoursesByCategoryId(id);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        if (categoryService.existsById(id)) {
            // Kiểm tra các khóa học liên quan
            List<Course> relatedCourses = categoryService.findCoursesByCategoryId(id);
            if (!relatedCourses.isEmpty()) {
                // Nếu có khóa học liên quan, có thể xử lý xóa ở đây
                for (Course course : relatedCourses) {
                    // Gọi phương thức xóa khóa học
                    courseService.deleteById(course.getCourseId());
                }
            }
            
            // Sau khi xóa các khóa học, xóa danh mục
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategories(@RequestParam String name) {
        List<Category> categories = categoryService.findByCategoryNameLike(name);
        return ResponseEntity.ok(categories);
    }
}