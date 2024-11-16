package com.poly.bean;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reviews")
public class Review implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer reviewId;

   @ManyToOne
   @JoinColumn(name = "CourseId")
   private Course course; // Liên kết đến Course

   @ManyToOne
   @JoinColumn(name = "StudentId")
   private User student; // Liên kết đến User (học viên)

   @Column(nullable = false)
   private Integer rating; // Đánh giá khóa học

   @Column(columnDefinition = "TEXT")
   private String comment; // Nhận xét của học viên.
}