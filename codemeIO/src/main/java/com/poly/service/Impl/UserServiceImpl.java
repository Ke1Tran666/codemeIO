package com.poly.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Role;
import com.poly.bean.User;
import com.poly.bean.UserRole;
import com.poly.repository.RoleRepository;
import com.poly.repository.UserRepository;
import com.poly.repository.UserRoleRepository;
import com.poly.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired 
    UserRepository userRepo;
    
    @Autowired 
    RoleRepository roleRepo;
    
    @Autowired 
    UserRoleRepository userRoleRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username); // Sử dụng phương thức mới
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepo.deleteByUsername(username); // Đảm bảo phương thức này tồn tại trong repository
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public List<User> findByFullname(String fullname) {
        return userRepo.findByFullname(fullname);
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return userRepo.findByUsernameLike(username);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public List<UserRole> findAllUserRoles() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public void deleteUserRole(Long id) {
        userRoleRepo.deleteById(id);
    }

    @Override
    public Long countUsersByType(String userType) {
        return userRepo.countByUserType(userType);
    }
}