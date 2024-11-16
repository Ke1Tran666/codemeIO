package com.poly.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Assignment;
import com.poly.bean.Submission;
import com.poly.bean.User;
import com.poly.repository.SubmissionRepository;
import com.poly.service.SubmissionService;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    
    @Autowired 
    private SubmissionRepository submissionRepo;

    @Override
    public List<Submission> findAll() {
        return submissionRepo.findAll();
    }

    @Override
    public Submission findById(Long id) {
        return submissionRepo.findById(id).orElse(null);
    }

    @Override
    public Submission save(Submission submission) {
        return submissionRepo.save(submission);
    }

    @Override
    public void deleteById(Long id) {
        submissionRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return submissionRepo.existsById(id);
    }

    @Override
    public List<Submission> findByAssignment(Assignment assignment) {
        return submissionRepo.findByAssignment(assignment);
    }

    @Override
    public List<Submission> findByStudent(User student) {
        return submissionRepo.findByStudent(student);
    }

    @Override
    public Double getAverageGradeForAssignment(Long assignmentId) {
        return submissionRepo.getAverageGradeByAssignmentId(assignmentId); // Phương thức này cần được định nghĩa trong repository
    }
}