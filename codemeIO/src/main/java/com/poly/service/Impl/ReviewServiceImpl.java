package com.poly.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Course;
import com.poly.bean.Review;
import com.poly.bean.User;
import com.poly.repository.ReviewRepository;
import com.poly.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    @Autowired 
    private ReviewRepository reviewRepo;

    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    public Review findById(Long id) {
        return reviewRepo.findById(id).orElse(null);
    }

    @Override
    public Review save(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return reviewRepo.existsById(id);
    }

    @Override
    public List<Review> findByCourse(Course course) {
        return reviewRepo.findByCourse(course);
    }

    @Override
    public List<Review> findByStudent(User student) {
        return reviewRepo.findByStudent(student);
    }

    @Override
    public Double getAverageRatingForCourse(Long courseId) {
        return reviewRepo.getAverageRatingByCourseId(courseId); // Phương thức này cần được định nghĩa trong repository
    }
}