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
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import com.poly.service.RoleService;
import com.poly.service.UserRoleService;
import com.poly.service.UserService;

import jakarta.mail.internet.MimeMessage;
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
    public ResponseEntity<User> login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            // Trả về thông tin người dùng nếu đăng nhập thành công
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(null); // Hoặc có thể trả về một đối tượng lỗi nếu cần
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setUserType("Student");
        
        // Kiểm tra nếu email đã tồn tại
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Kiểm tra nếu username đã tồn tại
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        User savedUser = userService.save(user);
        Role role = roleService.findByName("Student");

        if (role == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(role);
        userRole.setDateAssigned(new Date());

        userRoleService.save(userRole);
        return ResponseEntity.ok(savedUser);
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

        // Tạo mã OTP 6 chữ số
        String otp = String.format("%06d", (int)(Math.random() * 999999));

        // Lưu mã OTP vào tokenStore
        tokenStore.put(otp, email);

        // Gửi email với mã OTP
        try {
        	MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("OTP reset password");
            helper.setText("<p>Mã OTP của bạn là:</p>" +
                           "<p style='font-weight:bold;'>" + otp + "</p>" +
                           "<p>Vui lòng sử dụng mã này để đặt lại mật khẩu.</p>", true);
            mailSender.send(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể gửi email: " + e.getMessage());
        }

        return ResponseEntity.ok("Email đặt lại mật khẩu đã được gửi đến " + email);
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody Map<String, String> payload) {
        String otp = payload.get("otp");

        // Check if the OTP exists in the tokenStore
        String email = tokenStore.get(otp);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã OTP không hợp lệ hoặc đã hết hạn");
        }

        // If OTP is valid, return success message
        return ResponseEntity.ok("Mã OTP hợp lệ");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");

        // Tìm người dùng theo email
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Người dùng không tồn tại");
        }

        // Cập nhật mật khẩu
        user.setPassword(newPassword); // Bạn nên mã hóa mật khẩu trước khi lưu
        userService.save(user);

        return ResponseEntity.ok("Mật khẩu đã được cập nhật thành công");
    }
}