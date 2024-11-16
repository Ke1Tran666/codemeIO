package com.poly.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Assignment;
import com.poly.bean.Lesson;
import com.poly.repository.AssignmentRepository;
import com.poly.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    
    @Autowired 
    private AssignmentRepository assignmentRepo;

    @Override
    public List<Assignment> findAll() {
        return assignmentRepo.findAll();
    }

    @Override
    public Assignment findById(Long id) {
        return assignmentRepo.findById(id).orElse(null);
    }

    @Override
    public Assignment save(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }

    @Override
    public void deleteById(Long id) {
        assignmentRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return assignmentRepo.existsById(id);
    }

    @Override
    public List<Assignment> findByLesson(Lesson lesson) {
        return assignmentRepo.findByLesson(lesson);
    }

    @Override
    public List<Assignment> findByTitleLike(String title) {
        return assignmentRepo.findByTitleContaining(title); // Sử dụng phương thức phù hợp
    }
}