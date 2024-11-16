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
@Table(name = "Lessons")
public class Lesson implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer lessonId;

   @ManyToOne
   @JoinColumn(name = "CourseId")
   private Course course; // Liên kết đến Course

   @Column(nullable = false)
   private String title;

   @Column(columnDefinition = "TEXT")
   private String content; // Sử dụng columnDefinition cho NVARCHAR(MAX)

   private String linkVideo; // Đường dẫn đến video bài học.
}