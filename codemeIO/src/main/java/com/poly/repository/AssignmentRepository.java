package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.bean.Assignment;
import com.poly.bean.Lesson;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByLesson(Lesson lesson); // Tìm kiếm theo bài học

    List<Assignment> findByTitleContaining(String title); // Tìm kiếm theo tiêu đề
}