package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username); // Thêm phương thức này

    void deleteByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findByFullname(String fullname);

    List<User> findByUsernameLike(String username);

    Long countByUserType(String userType);
}