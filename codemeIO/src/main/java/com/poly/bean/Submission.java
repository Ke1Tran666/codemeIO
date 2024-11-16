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
@Table(name = "Submissions")
public class Submission implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private Integer submissionId; 

   @ManyToOne 
   @JoinColumn(name="AssignmentId") 
   private Assignment assignment; // Liên kết đến Assignment 

   @ManyToOne 
   @JoinColumn(name="StudentId") 
   private User student; // Liên kết đến User (học viên)

   @Column(columnDefinition="TEXT") 
   private String submissionContent; // Nội dung bài nộp

   private BigDecimal grade; // Điểm

   @Column(columnDefinition="TEXT") 
   private String feedback; // Phản hồi từ giảng viên
}