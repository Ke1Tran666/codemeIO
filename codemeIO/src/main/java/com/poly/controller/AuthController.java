package com.poly.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.service.RoleService;
import com.poly.service.UserRoleService;
import com.poly.service.UserService;
import com.poly.bean.Role;
import com.poly.bean.User;
import com.poly.bean.UserRole;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên người dùng hoặc mật khẩu không hợp lệ");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Thiết lập vai trò mặc định là "student"
        user.setUserType("student"); // Thiết lập userType cho người dùng

        // Lưu thông tin người dùng trước
        User savedUser = userService.save(user); 

        // Lấy đối tượng Role từ cơ sở dữ liệu
        Role role = roleService.findByName("student"); // Lấy vai trò "student"

        if (role == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vai trò không tồn tại");
        }

        // Tạo đối tượng UserRole mới và gán cho người dùng
        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(role); // Gán vai trò cho UserRole

        // Định dạng ngày hiện tại thành YYYY-MM-DD
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(new Date());
        
        // Chuyển đổi chuỗi thành đối tượng Date
        try {
            Date dateAssigned = dateFormat.parse(formattedDate);
            userRole.setDateAssigned(dateAssigned); // Gán ngày đã định dạng cho dateAssigned
        } catch (ParseException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần
        }

        // Lưu UserRole
        userRoleService.save(userRole); 

        return ResponseEntity.ok("Người dùng đã được đăng ký thành công");
    }
}