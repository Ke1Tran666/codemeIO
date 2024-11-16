package com.poly.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description; // Sử dụng columnDefinition cho NVARCHAR(MAX)

    private String imageCourses; // Đường dẫn đến hình ảnh khóa học

    @Column(nullable = false)
    private BigDecimal price; // Sử dụng BigDecimal cho giá

    private BigDecimal rating; // Sử dụng BigDecimal cho đánh giá

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category; // Liên kết đến Category

    @ManyToOne
    @JoinColumn(name = "InstructorId")
    private User instructor; // Liên kết đến User (giáo viên)

   // Các trường khác như TotalStudents sẽ được thêm vào nếu cần thiết.
}