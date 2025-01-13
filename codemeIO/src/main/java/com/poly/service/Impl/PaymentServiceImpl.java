package com.poly.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Course;
import com.poly.bean.Payment;
import com.poly.bean.User;
import com.poly.repository.PaymentRepository;
import com.poly.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Override
    public List<Payment> findAll() {
        return paymentRepo.findAll();
    }

    @Override
    public Payment findById(Integer id) { 
        return paymentRepo.findById(id).orElse(null);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public void deleteById(Integer id) { 
        paymentRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) { 
        return paymentRepo.existsById(id);
    }

    @Override
    public List<Payment> findByStudent(User student) {
        return paymentRepo.findByStudent(student);
    }

    @Override
    public List<Payment> findByCourse(Course course) {
        return paymentRepo.findByCourse(course);
    }

    @Override
    public List<Payment> findByPaymentDateBetween(Date startDate, Date endDate) {
        return paymentRepo.findByPaymentDateBetween(startDate, endDate);
    }

    @Override
    public Integer countPaymentsByStatus(boolean status) { 
        return paymentRepo.countByPaymentStatus(status);
    }
    
    @Override
    public List<Payment> findByStudentId(Integer studentId) {
        return paymentRepo.findByStudent_UserId(studentId);
    }

    @Override
    public boolean existsByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return paymentRepo.existsByStudent_UserIdAndCourse_CourseId(studentId, courseId);
    }
}