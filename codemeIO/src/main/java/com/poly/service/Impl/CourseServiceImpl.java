package com.poly.service.Impl;

import java.util.List;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Override
    public String saveUploadedFile(MultipartFile file) {
        String uploadDir = "uploads/";
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        try {
            // Tạo thư mục nếu chưa tồn tại
            Files.createDirectories(Paths.get(uploadDir));
            // Lưu file
            Path filePath = Paths.get(uploadDir + fileName);
            Files.copy(file.getInputStream(), filePath);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace(); // Log lỗi
            return null; // Hoặc ném ra ngoại lệ nếu cần
        }
    }
}