package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.bean.Assignment;
import com.poly.bean.Submission;
import com.poly.bean.User;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByAssignment(Assignment assignment); // Tìm kiếm theo bài tập

    List<Submission> findByStudent(User student); // Tìm kiếm theo sinh viên

    @Query("SELECT AVG(s.grade) FROM Submission s WHERE s.assignment.assignmentId = ?1")
    Double getAverageGradeByAssignmentId(Long assignmentId); // Tính điểm trung bình cho bài tập
}