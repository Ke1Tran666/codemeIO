package com.poly.service;

import java.util.List;
import com.poly.bean.UserRole;

public interface UserRoleService {

    List<UserRole> findAll(); // Lấy tất cả vai trò người dùng

    UserRole save(UserRole userRole); // Lưu một vai trò người dùng mới

    void deleteById(Integer id); // Xóa vai trò người dùng theo ID

    UserRole findById(Integer id); // Tìm vai trò người dùng theo ID

    List<UserRole> findByUserId(Integer userId); // Tìm vai trò người dùng theo userId
    
    void delete(UserRole userRole);
}