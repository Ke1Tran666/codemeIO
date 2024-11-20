package com.poly.service;

import java.util.List;
import com.poly.bean.UserRole;

public interface UserRoleService {

    List<UserRole> findAll(); // Lấy tất cả vai trò người dùng

    UserRole save(UserRole userRole); // Lưu một vai trò người dùng mới

    void deleteById(Long id); // Xóa vai trò người dùng theo ID

    UserRole findById(Long id); // Tìm vai trò người dùng theo ID
}