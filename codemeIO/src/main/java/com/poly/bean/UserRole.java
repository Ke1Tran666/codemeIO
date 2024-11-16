package com.poly.bean;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserRoles")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleId;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "RoleId", nullable = false)
    private Role role;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateAssigned;
}