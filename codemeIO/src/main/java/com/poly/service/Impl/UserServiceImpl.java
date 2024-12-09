package com.poly.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poly.bean.Role;
import com.poly.bean.User;
import com.poly.bean.UserRole;
import com.poly.repository.RoleRepository;
import com.poly.repository.UserRepository;
import com.poly.repository.UserRoleRepository;
import com.poly.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired 
    UserRepository userRepo;
    
    @Autowired 
    RoleRepository roleRepo;
    
    @Autowired 
    UserRoleRepository userRoleRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    
    @Override
    public User findById(Integer userId) { // Thêm phương thức này
        return userRepo.findById(userId).orElse(null); // Trả về null nếu không tìm thấy
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
    @Override
    public User findByEmail(String email) {
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        return userRepo.findByEmail(email.toLowerCase());
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteById(Integer userId) {
        userRepo.deleteById(userId); // Giả sử bạn sử dụng Spring Data JPA
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }
    
    @Override
    public boolean existsByEmail(String email) {
    	return userRepo.findByEmail(email) != null;     
    }

    @Override
    public List<User> findByFullname(String fullname) {
        return userRepo.findByFullname(fullname);
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return userRepo.findByUsernameLike(username);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public List<UserRole> findAllUserRoles() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public void deleteUserRole(Integer id) {
        userRoleRepo.deleteById(id);
    }

    @Override
    public Long countUsersByType(String userType) {
        return userRepo.countByUserType(userType);
    }
    
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Hoặc trả về thông tin người dùng cần thiết
        }
        return null; // Trả về null nếu không tìm thấy người dùng hoặc mật khẩu sai
    }
    
    @Override
    public String saveImage(MultipartFile file) {
        String uploadDir = "uploads/"; // Thư mục lưu trữ hình ảnh
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        // Tạo tên tệp duy nhất
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);

        try {
            Files.write(path, file.getBytes()); // Lưu tệp
            return "/uploads/" + fileName; // Trả về URL hình ảnh
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể lưu hình ảnh");
        }
    }
    
    @Override
    public boolean existsById(Integer userId) {
        return userRepo.existsById(userId); // Nếu bạn sử dụng Spring Data JPA
    }
}