package com.poly.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.poly.bean.Course;
import com.poly.service.CourseService;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.findAll(); // Trả về danh sách khóa học
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        Course course = courseService.findById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course savedCourse = courseService.save(course);
        return ResponseEntity.status(201).body(savedCourse); // Trả về khóa học đã được thêm
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course courseDetails) {
        Course existingCourse = courseService.findById(id);
        if (existingCourse == null) {
            return ResponseEntity.notFound().build();
        }

        // Kiểm tra và cập nhật từng thuộc tính, chỉ cập nhật nếu giá trị không phải là null
        if (courseDetails.getTitle() != null) {
            existingCourse.setTitle(courseDetails.getTitle());
        }
        if (courseDetails.getContent() != null) {
            existingCourse.setContent(courseDetails.getContent());
        }
        if (courseDetails.getPrice() != null) {
            existingCourse.setPrice(courseDetails.getPrice());
        } else {
            return ResponseEntity.badRequest().body(null); // Trả về lỗi nếu giá trị Price là null
        }
        // Cập nhật các thuộc tính khác nếu cần

        Course updatedCourse = courseService.save(existingCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        if (!courseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteById(id);
        return ResponseEntity.noContent().build(); // Trả về 204 No Content
    }

    @GetMapping("/courses/search")
    public List<Course> searchCourses(@RequestParam String title) {
        return courseService.findByTitle(title); // Tìm kiếm theo tiêu đề
    }

    @PostMapping("/courses/{id}/upload-image")
    public ResponseEntity<Map<String, Object>> uploadImage(
            @PathVariable Integer id, 
            @RequestParam("file") MultipartFile file) {
        
        Course course = courseService.findById(id);
        if (course == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Course not found"));
        }

        if (file == null || !file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid file type. Please upload an image."));
        }

        try {
            String fileName = courseService.saveUploadedFile(file);
            course.setImageCourses("/uploads/" + fileName);
            courseService.save(course);
            return ResponseEntity.ok(Map.of(
                "message", "Image updated successfully",
                "imageUrl", course.getImageCourses()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Failed to upload image"));
        }
    }
}