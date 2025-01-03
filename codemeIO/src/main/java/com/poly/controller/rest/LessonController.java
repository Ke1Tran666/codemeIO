package com.poly.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.bean.Course;
import com.poly.bean.Lesson;
import com.poly.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.findAll(); // Trả về danh sách tất cả các bài học
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Integer id) {
        return lessonService.findById(id); // Lấy bài học theo ID
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
    	Lesson savedLesson = lessonService.save(lesson);
        return ResponseEntity.status(201).body(savedLesson);
    }

    @PutMapping("/{id}")
    public Lesson updateLesson(@PathVariable Integer id, @RequestBody Lesson lesson) {
        lesson.setLessonId(id); // Thiết lập ID cho bài học
        return lessonService.save(lesson); // Cập nhật bài học
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Integer id) {
        lessonService.deleteById(id); // Xóa bài học theo ID
    }

    @GetMapping("/course/{courseId}")
    public List<Lesson> getLessonsByCourse(@PathVariable Integer courseId) {
        Course course = new Course();
        course.setCourseId(courseId); // Thiết lập ID khóa học
        return lessonService.findByCourse(course); // Tìm các bài học theo khóa học
    }

    @GetMapping("/search")
    public List<Lesson> searchLessons(@RequestParam String title) {
        return lessonService.findByTitleLike(title); // Tìm kiếm theo tiêu đề
    }
}