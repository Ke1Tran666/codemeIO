package com.poly.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Lesson> getAllLesson() {
        return lessonService.findAll(); // Trả về danh sách khóa học
    }

    @GetMapping("/course/{courseId}")
    public List<Lesson> getLessonsByCourse(@PathVariable Integer courseId) {
        Course course = new Course();
        course.setCourseId(courseId); // Giả sử có phương thức để thiết lập ID
        return lessonService.findByCourse(course);
    }
}
