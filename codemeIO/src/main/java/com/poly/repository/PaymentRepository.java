package com.poly.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.bean.Course;
import com.poly.bean.Payment;
import com.poly.bean.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudent(User student); // Tìm kiếm theo sinh viên

    List<Payment> findByCourse(Course course); // Tìm kiếm theo khóa học

    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate); // Tìm kiếm theo ngày thanh toán

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.course.courseId = ?1")
    BigDecimal getTotalPaymentsByCourseId(Long courseId); // Tính tổng số tiền thanh toán cho khóa học

    Long countByPaymentStatus(String status); // Đếm số lượng thanh toán theo trạng thái
}