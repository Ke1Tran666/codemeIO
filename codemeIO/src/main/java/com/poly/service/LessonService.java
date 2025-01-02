package com.poly.service;

import java.util.List;

import com.poly.bean.Course;
import com.poly.bean.Lesson;

public interface LessonService {

    List<Lesson> findAll();
    
    Lesson findById(Integer id);
    
    Lesson save(Lesson lesson);
    
    void deleteById(Integer id);

    boolean existsById(Integer id);

    List<Lesson> findByTitleLike(String title);

    List<Lesson> findByCourse(Course course);
   
}