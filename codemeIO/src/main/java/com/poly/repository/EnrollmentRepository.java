package com.poly.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.bean.Course;
import com.poly.bean.Enrollment;
import com.poly.bean.User;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(User student); // Tìm kiếm theo sinh viên

    List<Enrollment> findByCourse(Course course); // Tìm kiếm theo khóa học

    List<Enrollment> findByEnrollmentDateBetween(Date startDate, Date endDate); // Tìm kiếm theo ngày đăng ký

    Long countByCourse_CourseId(Long courseId); // Đếm số lượng đăng ký theo khóa học
}