package com.poly.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.bean.Course;
import com.poly.bean.Payment;
import com.poly.bean.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByStudent(User student); // Tìm kiếm theo sinh viên

    List<Payment> findByCourse(Course course); // Tìm kiếm theo khóa học

    List<Payment> findByPaymentDateBetween(Date startDate, Date endDate); // Tìm kiếm theo ngày thanh toán

    Integer countByPaymentStatus(boolean status); // Đếm số lượng thanh toán theo trạng thái (true: thành công, false: thất bại)
    
    List<Payment> findByStudent_UserId(Integer studentId);
    
    boolean existsByStudent_UserIdAndCourse_CourseId(Integer studentId, Integer courseId);
}