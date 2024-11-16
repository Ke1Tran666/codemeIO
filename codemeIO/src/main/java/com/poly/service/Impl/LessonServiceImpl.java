package com.poly.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Course;
import com.poly.bean.Lesson;
import com.poly.repository.LessonRepository;
import com.poly.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
    
    @Autowired 
    private LessonRepository lessonRepo;

    @Override
    public List<Lesson> findAll() {
        return lessonRepo.findAll();
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepo.findById(id).orElse(null);
    }

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepo.save(lesson);
    }

    @Override
    public void deleteById(Long id) {
        lessonRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return lessonRepo.existsById(id);
    }

    @Override
    public List<Lesson> findByTitleLike(String title) {
        return lessonRepo.findByTitleContaining(title); // Sử dụng phương thức phù hợp
    }

    @Override
    public List<Lesson> findByCourse(Course course) {
        return lessonRepo.findByCourse(course); // Tìm kiếm theo khóa học
    }
}