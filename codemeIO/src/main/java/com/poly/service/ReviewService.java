package com.poly.service;

import java.util.List;

import com.poly.bean.Course;
import com.poly.bean.Review;
import com.poly.bean.User;

public interface ReviewService {

    List<Review> findAll();
    
    Review findById(Long id);
    
    Review save(Review review);
    
    void deleteById(Long id);

    boolean existsById(Long id);

    List<Review> findByCourse(Course course);

    List<Review> findByStudent(User student);

    Double getAverageRatingForCourse(Long courseId);
}