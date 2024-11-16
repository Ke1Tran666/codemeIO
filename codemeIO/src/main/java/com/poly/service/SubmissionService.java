package com.poly.service;

import java.util.List;

import com.poly.bean.Assignment;
import com.poly.bean.Submission;
import com.poly.bean.User;

public interface SubmissionService {

    List<Submission> findAll();

    Submission findById(Long id);

    Submission save(Submission submission);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<Submission> findByAssignment(Assignment assignment);

    List<Submission> findByStudent(User student);

    Double getAverageGradeForAssignment(Long assignmentId);
}