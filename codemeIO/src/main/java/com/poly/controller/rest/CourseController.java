package com.poly.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.bean.Category;
import com.poly.bean.Course;
import com.poly.service.CategoryService;
import com.poly.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
    private CategoryService categoryService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.findAll(); // Trả về danh sách khóa học
	}
}
