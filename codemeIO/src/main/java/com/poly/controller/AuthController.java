package com.poly.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
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
        
        // Kiểm tra sự tồn tại của email
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateAssigned = dateFormat.parse(dateFormat.format(new Date()));
            userRole.setDateAssigned(dateAssigned);
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi gán ngày");
        }

        userRoleService.save(userRole);
        return ResponseEntity.ok("Người dùng đã được đăng ký thành công");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        
        if (email == null || !email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email không hợp lệ");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại");
        }

        String token = UUID.randomUUID().toString();
        tokenStore.put(token, email);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Yêu cầu đặt lại mật khẩu");
            String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
            message.setText("Vui lòng nhấp vào liên kết sau để đặt lại mật khẩu: " +
                            "http://yourdomain.com/reset-password?token=" + token + "&email=" + encodedEmail);
            mailSender.send(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể gửi email: " + e.getMessage());
        }

        return ResponseEntity.ok("Email đặt lại mật khẩu đã được gửi");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody Map<String, String> payload) {
        String newPassword = payload.get("newPassword");
        String email = tokenStore.get(token);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token không hợp lệ hoặc đã hết hạn");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Người dùng không tồn tại");
        }

        user.setPassword(newPassword);
        userService.save(user);
        tokenStore.remove(token);

        return ResponseEntity.ok("Mật khẩu đã được cập nhật thành công");
    }
}