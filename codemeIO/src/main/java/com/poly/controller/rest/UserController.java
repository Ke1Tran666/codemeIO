package com.poly.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.poly.bean.User;
import com.poly.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    
//	@GetMapping("/check-email")
//  public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
//      boolean exists = userService.existsByEmail(email);
//      Map<String, Boolean> response = new HashMap<>();
//      response.put("exists", exists);
//      return ResponseEntity.ok(response);
//  }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id); // Tìm người dùng theo ID
        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            response.put("userId", user.getUserId()); // Thêm ID vào phản hồi
            response.put("fullname", user.getFullname());
            response.put("email", user.getEmail());
            response.put("phone", user.getPhone());
            response.put("photo", user.getPhoto());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        User user = userService.findById(id); // Tìm người dùng
        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            // Cập nhật thông tin người dùng
            if (updates.containsKey("fullname")) {
                user.setFullname((String) updates.get("fullname"));
            }
            if (updates.containsKey("email")) {
                user.setEmail((String) updates.get("email"));
            }
            if (updates.containsKey("phone")) {
                user.setPhone((String) updates.get("phone"));
            }
            if (updates.containsKey("password")) {
                user.setPassword((String) updates.get("password")); // Nên mã hóa mật khẩu
            }

            userService.save(user); // Lưu thông tin người dùng đã cập nhật

            response.put("message", "User updated successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }
    
    @PutMapping("/user/{id}/image")
    public ResponseEntity<Map<String, Object>> updateUserImage(
            @PathVariable Integer id, 
            @RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.findById(id);

        if (user != null) {
            try {
                String imageUrl = userService.saveImage(file); // Lưu hình ảnh và nhận URL
                user.setPhoto(imageUrl); // Cập nhật URL hình ảnh trong user
                userService.save(user); // Lưu thông tin người dùng
                
                // Thêm imageUrl vào phản hồi
                response.put("imageUrl", imageUrl); // Đảm bảo trả về imageUrl
                response.put("message", "Image updated successfully");
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                response.put("message", "Failed to upload image");
                return ResponseEntity.status(500).body(response);
            }
        } else {
            response.put("message", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }
}