package com.poly.service;

import com.poly.bean.Role;

public interface RoleService {
    Role findByName(String roleName); // Tìm vai trò theo tên
    // Bạn có thể thêm các phương thức khác nếu cần
}