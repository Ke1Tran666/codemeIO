package com.poly.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.poly.service.RoleService;
import com.poly.service.UserRoleService;
import com.poly.service.UserService;

import jakarta.servlet.http.HttpServletRequest; 
import com.poly.bean.Role;
import com.poly.bean.User;
import com.poly.bean.UserRole;

@RestController
@RequestMapping("/api")
public class AuthController {

    private Map<String, String> tokenStore = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên người dùng hoặc mật khẩu không hợp lệ");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        user.setUserType("student"); 
        
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email đã tồn tại.");
        }

        User savedUser = userService.save(user);
        Role role = roleService.findByName("student");

        if (role == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vai trò không tồn tại");
        }

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(role); 

        userRole.setDateAssigned(new Date());

        userRoleService.save(userRole);
        return ResponseEntity.ok("Người dùng đã được đăng ký thành công");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");

        // Kiểm tra tính hợp lệ của email
        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email không hợp lệ");
        }

        // Tìm người dùng theo email
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại");
        }

        // Tạo token mới
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, email); // Lưu token vào tokenStore

        // Gửi email với token
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Yêu cầu đặt lại mật khẩu");
            message.setText("Token của bạn là: " + token + "\nVui lòng nhấp vào liên kết sau để đặt lại mật khẩu: " +
                    "http://localhost:5173/reset-password?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8));
            mailSender.send(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể gửi email: " + e.getMessage());
        }

        return ResponseEntity.ok("Email đặt lại mật khẩu đã được gửi đến " + email);
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        String newPassword = payload.get("newPassword");

        // Kiểm tra token
        String email = tokenStore.get(token);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token không hợp lệ hoặc đã hết hạn");
        }

        // Cập nhật mật khẩu
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Người dùng không tồn tại");
        }

        user.setPassword(newPassword); // Bạn nên mã hóa mật khẩu trước khi lưu
        userService.save(user);

        // Xóa token sau khi cập nhật mật khẩu
        tokenStore.remove(token);

        return ResponseEntity.ok("Mật khẩu đã được cập nhật thành công");
    }
}