package com.poly.service.Impl;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Course;
import com.poly.bean.Enrollment;
import com.poly.bean.User;
import com.poly.repository.EnrollmentRepository;
import com.poly.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    
    @Autowired 
    private EnrollmentRepository enrollmentRepo;

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepo.findAll();
    }

    @Override
    public Enrollment findById(Long id) {
        return enrollmentRepo.findById(id).orElse(null);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepo.save(enrollment);
    }

    @Override
    public void deleteById(Long id) {
        enrollmentRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return enrollmentRepo.existsById(id);
    }

    @Override
    public List<Enrollment> findByStudent(User student) {
        return enrollmentRepo.findByStudent(student);
    }

    @Override
    public List<Enrollment> findByCourse(Course course) {
        return enrollmentRepo.findByCourse(course);
    }

    @Override
    public List<Enrollment> findByEnrollmentDateBetween(Date startDate, Date endDate) {
        return enrollmentRepo.findByEnrollmentDateBetween(startDate, endDate);
    }

    @Override
    public Long countEnrollmentsByCourse(Long courseId) {
        return enrollmentRepo.countByCourse_CourseId(courseId);
    }
}