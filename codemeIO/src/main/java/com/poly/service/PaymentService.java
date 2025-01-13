package com.poly.service;

import java.util.Date;
import java.util.List;

import com.poly.bean.Course;
import com.poly.bean.Payment;
import com.poly.bean.User;

public interface PaymentService {

    List<Payment> findAll();

    Payment findById(Integer id); 

    Payment save(Payment payment);

    void deleteById(Integer id); 

    boolean existsById(Integer id); 

    List<Payment> findByStudent(User student);

    List<Payment> findByCourse(Course course);

    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate);

    Integer countPaymentsByStatus(boolean status); 
    
    List<Payment> findByStudentId(Integer studentId);

    // Thêm phương thức kiểm tra tồn tại Payment theo studentId và courseId
    boolean existsByStudentIdAndCourseId(Integer studentId, Integer courseId);
}