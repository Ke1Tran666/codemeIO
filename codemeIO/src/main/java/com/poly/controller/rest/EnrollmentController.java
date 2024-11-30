package com.poly.controller.rest;

import com.poly.bean.Course;
import com.poly.bean.Enrollment;
import com.poly.bean.User;
import com.poly.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Lấy tất cả các đăng ký
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.findAll();
    }

    // Lấy thông tin đăng ký theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.findById(id);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    // Tạo mới một đăng ký
    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.save(enrollment);
    }

    // Xóa đăng ký theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        if (enrollmentService.existsById(id)) {
            enrollmentService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 // Tìm các khóa học theo sinh viên
    @GetMapping("/student/{studentId}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudent(@PathVariable Integer studentId) {
        User student = new User();
        student.setUserId(studentId); // Thiết lập ID cho sinh viên

        List<Enrollment> enrollments = enrollmentService.findByStudent(student);
        List<Course> courses = enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(courses);
    }
}