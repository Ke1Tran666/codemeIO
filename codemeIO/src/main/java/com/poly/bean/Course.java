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
    @Column(name = "CourseId")
    private Integer courseId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content; // Sử dụng NVARCHAR(MAX) cho mô tả

    @Column(name = "ImageCourses") // Đảm bảo tên cột đúng
    private String imageCourses; // Đường dẫn đến hình ảnh khóa học

    @Column(nullable = false)
    private BigDecimal price; // Sử dụng BigDecimal cho giá

    private BigDecimal rating; // Sử dụng BigDecimal cho đánh giá

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category; // Liên kết đến Category

    @ManyToOne
    @JoinColumn(name = "InstructorId")
    private User instructor; // Liên kết đến User (giáo viên)

    @Column(name = "TotalStudents", nullable = false)
    private Integer totalStudents = 0; // Số lượng sinh viên tham gia khóa học, mặc định là 0

   // Các trường khác sẽ được thêm vào nếu cần thiết.
}