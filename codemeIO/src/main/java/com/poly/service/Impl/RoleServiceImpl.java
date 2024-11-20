package com.poly.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.bean.Role;
import com.poly.repository.RoleRepository; // Giả sử bạn đã tạo repository cho Role
import com.poly.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByRoleName(roleName); // Giả sử bạn đã có phương thức này trong repository
    }
}
