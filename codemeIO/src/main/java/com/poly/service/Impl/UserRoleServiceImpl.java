package com.poly.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.bean.UserRole;
import com.poly.repository.UserRoleRepository;
import com.poly.service.UserRoleService;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void deleteById(Integer id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public UserRole findById(Integer id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserRole> findByUserId(Integer userId) {
        return userRoleRepository.findByUser_UserId(userId); // Giả sử bạn đã định nghĩa phương thức này trong repository
    }
    
    @Override
    public void delete(UserRole userRole) {
        userRoleRepository.delete(userRole); // Nếu bạn sử dụng Spring Data JPA
    }
}