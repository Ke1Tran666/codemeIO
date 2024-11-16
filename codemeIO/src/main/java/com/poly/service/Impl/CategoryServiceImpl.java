package com.poly.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Category;
import com.poly.repository.CategoryRepository;
import com.poly.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired 
    private CategoryRepository categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepo.existsById(id);
    }

    @Override
    public List<Category> findByCategoryNameLike(String categoryName) {
        return categoryRepo.findByCategoryNameContaining(categoryName);
    }
}