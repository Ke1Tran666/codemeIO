package com.poly.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.bean.Role;
import com.poly.repository.RoleRepository; 
import com.poly.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role); // Lưu vai trò mới
    }

    @Override
    public Role update(Integer roleId, Role roleDetails) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            Role existingRole = optionalRole.get();
            existingRole.setRoleName(roleDetails.getRoleName()); // Cập nhật tên vai trò
            return roleRepository.save(existingRole); // Lưu lại vai trò đã cập nhật
        }
        return null; // Nếu không tìm thấy vai trò
    }

    @Override
    public boolean delete(Integer roleId) {
        if (roleRepository.existsById(roleId)) {
            roleRepository.deleteById(roleId); // Xóa vai trò
            return true;
        }
        return false; // Nếu không tìm thấy vai trò để xóa
    }
    
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll(); // Lấy tất cả vai trò
    }
    
    @Override
    public Role findById(Integer roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        return optionalRole.orElse(null); // Trả về vai trò nếu tìm thấy, ngược lại trả về null
    }
}