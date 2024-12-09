package com.poly.controller.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.bean.Role;
import com.poly.bean.UserRole;
import com.poly.service.UserRoleService;
import com.poly.service.RoleService;
import com.poly.service.UserService;

@RestController
@RequestMapping("/api/userRoles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    // Lấy vai trò của người dùng theo userId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Role>> getUserRolesByUserId(@PathVariable Integer userId) {
        List<UserRole> userRoles = userRoleService.findByUserId(userId);

        // Nếu không có roles, trả về danh sách rỗng
        List<Role> roles = userRoles.stream()
            .map(UserRole::getRole)
            .filter(role -> role != null)
            .collect(Collectors.toList());

        return ResponseEntity.ok(roles);
    }

    // Chỉnh sửa vai trò của người dùng
    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUserRoles(@PathVariable Integer userId, 
                                                  @RequestBody UpdateUserRolesRequest request) {
        // Kiểm tra người dùng
        if (!userService.existsById(userId)) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Tìm các vai trò hiện tại
        List<UserRole> currentUserRoles = userRoleService.findByUserId(userId);

        // Thêm vai trò mới
        for (String roleName : request.getNewRoles()) {
            Role role = roleService.findByName(roleName);
            if (role != null) {
                boolean roleExists = currentUserRoles.stream()
                    .anyMatch(userRole -> userRole.getRole().getRoleName().equals(roleName));
                if (!roleExists) {
                    UserRole newUserRole = new UserRole();
                    newUserRole.setUser(userService.findById(userId));
                    newUserRole.setRole(role);
                    newUserRole.setDateAssigned(new Date()); // Gán thời gian hiện tại
                    userRoleService.save(newUserRole);
                }
            }
        }

        // Xóa vai trò không còn được chọn
        for (UserRole userRole : currentUserRoles) {
            if (!request.getNewRoles().contains(userRole.getRole().getRoleName())) {
                userRoleService.delete(userRole);
            }
        }

        return ResponseEntity.ok("User roles updated successfully");
    }

    // DTO cho yêu cầu cập nhật vai trò
    public static class UpdateUserRolesRequest {
        private List<String> newRoles;

        // Getters và setters
        public List<String> getNewRoles() {
            return newRoles;
        }

        public void setNewRoles(List<String> newRoles) {
            this.newRoles = newRoles;
        }
    }
}