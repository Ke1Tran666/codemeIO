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
@Table(name = "Assignments")
public class Assignment implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer assignmentId;

   @ManyToOne
   @JoinColumn(name = "LessonId")
   private Lesson lesson; // Liên kết đến Lesson

   @Column(nullable = false)
   private String title;

   @Column(columnDefinition = "TEXT")
   private String description; // Mô tả bài tập.
}