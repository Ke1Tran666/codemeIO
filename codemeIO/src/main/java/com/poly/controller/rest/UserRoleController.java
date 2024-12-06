package com.poly.controller.rest;

import com.poly.bean.UserRole;
import com.poly.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    // Lấy vai trò của người dùng theo UserId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<UserRole>> getUserRolesByUserId(@PathVariable Long userId) {
        List<UserRole> userRoles = userRoleService.findAll(); // Thay bằng phương thức tìm kiếm theo userId nếu có
        // Lọc theo userId
        userRoles = userRoles.stream()
                .filter(userRole -> userRole.getUser().getUserId().equals(userId))
                .toList();

        if (userRoles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRoles);
    }
}