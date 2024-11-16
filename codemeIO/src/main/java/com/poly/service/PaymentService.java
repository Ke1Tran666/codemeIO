package com.poly.service;

import java.util.Date;
import java.util.List;

import com.poly.bean.Course;
import com.poly.bean.Payment;
import com.poly.bean.User;

import java.math.BigDecimal;

public interface PaymentService {

    List<Payment> findAll();

    Payment findById(Long id);

    Payment save(Payment payment);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<Payment> findByStudent(User student);

    List<Payment> findByCourse(Course course);

    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate);

    BigDecimal getTotalPaymentsForCourse(Long courseId);

    Long countPaymentsByStatus(String status);
}