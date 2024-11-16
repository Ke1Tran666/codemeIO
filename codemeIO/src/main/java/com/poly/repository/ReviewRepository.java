package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.bean.Course;
import com.poly.bean.Review;
import com.poly.bean.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByCourse(Course course); // Tìm kiếm theo khóa học

    List<Review> findByStudent(User student); // Tìm kiếm theo sinh viên

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.course.courseId = ?1")
    Double getAverageRatingByCourseId(Long courseId); // Tính điểm trung bình cho khóa học
}