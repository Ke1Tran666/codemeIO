package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.bean.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}