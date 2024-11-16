package com.poly.service;

import java.util.List;

import com.poly.bean.Role;
import com.poly.bean.User;
import com.poly.bean.UserRole;

public interface UserService {

    List<User> findAll();
    
    User findByUsername(String username);
    
    User save(User user);
    
    void deleteByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findByFullname(String fullname);

    List<User> findByUsernameLike(String username);

    List<Role> findAllRoles();

    List<UserRole> findAllUserRoles();

    UserRole saveUserRole(UserRole userRole);

    void deleteUserRole(Long id);

    Long countUsersByType(String userType);
}