package com.poly.service;

import java.util.List;

import com.poly.bean.Course;
import com.poly.bean.Enrollment;
import com.poly.bean.User;

import java.util.Date;

public interface EnrollmentService {

    List<Enrollment> findAll();
    
    Enrollment findById(Long id);
    
    Enrollment save(Enrollment enrollment);
    
    void deleteById(Long id);

    boolean existsById(Long id);

    List<Enrollment> findByStudent(User student);

    List<Enrollment> findByCourse(Course course);

    List<Enrollment> findByEnrollmentDateBetween(Date startDate, Date endDate);

    Long countEnrollmentsByCourse(Long courseId);
}