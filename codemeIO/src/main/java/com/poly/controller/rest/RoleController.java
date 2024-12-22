package com.poly.controller.rest;

import com.poly.bean.Role;
import com.poly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    // lấy Vai trò theo Tên
    @GetMapping("/{roleName}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String roleName) {
        Role role = roleService.findByName(roleName);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = roleService.save(role);
        return ResponseEntity.status(201).body(savedRole); // 201 Created
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer roleId, @RequestBody Role roleDetails) {
        Role updatedRole = roleService.update(roleId, roleDetails);
        if (updatedRole == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer roleId) {
        boolean isDeleted = roleService.delete(roleId);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}