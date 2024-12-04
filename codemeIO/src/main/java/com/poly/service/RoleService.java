package com.poly.service;

import com.poly.bean.Role;
import java.util.List;

public interface RoleService {
    Role findByName(String roleName);
    Role save(Role role);
    Role update(Integer roleId, Role roleDetails);
    boolean delete(Integer roleId);
    List<Role> findAll(); // Thêm phương thức này
}