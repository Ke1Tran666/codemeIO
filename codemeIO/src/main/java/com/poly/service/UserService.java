package com.poly.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.poly.bean.Role;
import com.poly.bean.User;
import com.poly.bean.UserRole;

public interface UserService {

    List<User> findAll();
    
    User findById(Integer userId);
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
    User save(User user); // Lưu một người dùng mới
    
    void deleteById(Integer userId);

    boolean existsByUsername(String username); // Kiểm tra xem tên người dùng đã tồn tại
    
    boolean existsByEmail(String email);
    
    boolean existsById(Integer userId);

    List<User> findByFullname(String fullname);

    List<User> findByUsernameLike(String username);

    List<Role> findAllRoles();

    List<UserRole> findAllUserRoles();

    UserRole saveUserRole(UserRole userRole);

    void deleteUserRole(Integer id);

    Long countUsersByType(String userType);
    
    User login(String username, String password); // Phương thức này sẽ trả về User nếu đăng nhập thành công
    
    String saveImage(MultipartFile file);
    
    List<User> findByStatus(String status);
    
}