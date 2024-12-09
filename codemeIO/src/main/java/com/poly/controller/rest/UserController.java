package com.poly.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.poly.bean.User;
import com.poly.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Lấy tất cả User
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Kiểm tra User tồn tại theo username
    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        boolean exists = userService.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    // Kiểm tra User tồn tại theo email
    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        boolean exists = userService.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    // Tạo mới User
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        if (userService.existsByEmail(user.getEmail())) {
            response.put("message", "Email already exists");
            return ResponseEntity.status(400).body(response);
        }

        if (userService.existsByUsername(user.getUsername())) {
            response.put("message", "Username already exists");
            return ResponseEntity.status(400).body(response);
        }

        userService.save(user);
        response.put("user", user);
        response.put("message", "User created successfully");
        return ResponseEntity.status(201).body(response);
    }

    // Lấy User theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.ok(Map.of(
            "userId", user.getUserId(),
            "fullname", user.getFullname(),
            "email", user.getEmail(),
            "phone", user.getPhone(),
            "photo", user.getPhoto(),
            "specialization", user.getSpecialization(), // Thêm trường specialization
            "yearsOfExperience", user.getYearsOfExperience(), // Thêm trường yearsOfExperience
            "department", user.getDepartment(), // Thêm trường department
            "status", user.getStatus() // Thêm trường status
        ));
    }

    // Cập nhật User
 // Cập nhật User
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        if (updates.containsKey("fullname")) {
            user.setFullname((String) updates.get("fullname"));
        }
        if (updates.containsKey("email")) {
            String newEmail = (String) updates.get("email");
            if (!newEmail.equals(user.getEmail()) && userService.existsByEmail(newEmail)) {
                return ResponseEntity.status(400).body(Map.of("message", "Email already exists"));
            }
            user.setEmail(newEmail);
        }
        if (updates.containsKey("username")) {
            String newUsername = (String) updates.get("username");
            if (!newUsername.equals(user.getUsername()) && userService.existsByUsername(newUsername)) {
                return ResponseEntity.status(400).body(Map.of("message", "Username already exists"));
            }
            user.setUsername(newUsername);
        }
        if (updates.containsKey("password")) {
            user.setPassword((String) updates.get("password")); // Sử dụng service để mã hóa
        }

        // Cập nhật các trường mới
        if (updates.containsKey("specialization")) {
            user.setSpecialization((String) updates.get("specialization"));
        }
        if (updates.containsKey("yearsOfExperience")) {
            Object yearsOfExperience = updates.get("yearsOfExperience");
            if (yearsOfExperience instanceof Integer) {
                user.setYearsOfExperience((Integer) yearsOfExperience);
            } else if (yearsOfExperience instanceof String) {
                try {
                    user.setYearsOfExperience(Integer.parseInt((String) yearsOfExperience));
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(400).body(Map.of("message", "Invalid format for years of experience"));
                }
            } else {
                return ResponseEntity.status(400).body(Map.of("message", "Invalid type for years of experience"));
            }
        }
        if (updates.containsKey("department")) {
            user.setDepartment((String) updates.get("department"));
        }
        if (updates.containsKey("status")) {
            user.setStatus((String) updates.get("status"));
        }

        userService.save(user);
        return ResponseEntity.ok(Map.of("message", "User updated successfully"));
    }

    // Xóa User
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id) {
        if (userService.findById(id) == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
        userService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    // Tìm kiếm User
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false) String fullname) {
        List<User> users = fullname != null && !fullname.isEmpty()
            ? userService.findByFullname(fullname)
            : userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Cập nhật hình ảnh User
    @PutMapping("/{id}/image")
    public ResponseEntity<Map<String, Object>> updateUserImage(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid file type. Please upload an image."));
        }

        try {
            String imageUrl = userService.saveImage(file);
            user.setPhoto(imageUrl);
            userService.save(user);
            return ResponseEntity.ok(Map.of(
                "message", "Image updated successfully",
                "imageUrl", imageUrl
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Failed to upload image"));
        }
    }
}