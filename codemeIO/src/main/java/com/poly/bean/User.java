package com.poly.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String gender;
    private String photo;
    private String status;
    
    @Column(name = "userType")
    private String userType;

    @Temporal(TemporalType.DATE)
    private Date startDate; // Thêm thuộc tính startDate

    private String specialization; // Thêm thuộc tính specialization
    private Integer yearsOfExperience; // Thêm thuộc tính yearsOfExperience
    private String department; // Thêm thuộc tính department

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @JsonIgnore
    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;
}