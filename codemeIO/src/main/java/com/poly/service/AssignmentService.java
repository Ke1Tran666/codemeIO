package com.poly.service;

import java.util.List;

import com.poly.bean.Assignment;
import com.poly.bean.Lesson;

public interface AssignmentService {

    List<Assignment> findAll();
    
    Assignment findById(Long id);
    
    Assignment save(Assignment assignment);
    
    void deleteById(Long id);

    boolean existsById(Long id);

    List<Assignment> findByLesson(Lesson lesson);

    List<Assignment> findByTitleLike(String title);
}