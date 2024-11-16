package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.bean.Course;
import com.poly.bean.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByTitleContaining(String title); // Tìm kiếm theo tiêu đề

    List<Lesson> findByCourse(Course course); // Tìm kiếm theo khóa học
}