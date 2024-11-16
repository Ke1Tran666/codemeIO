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
@Table(name="Enrollments") 
public class Enrollment implements Serializable { 
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY) 
     private Integer enrollmentId; 

     @ManyToOne 
     @JoinColumn(name="StudentId") 
     private User student; // Liên kết đến User (học viên) 

     @ManyToOne 
     @JoinColumn(name="CourseId") 
     private Course course; // Liên kết đến Course 

     @Temporal(TemporalType.DATE) 
     @Column(nullable=false) 
     private Date enrollmentDate; 

     @Temporal(TemporalType.DATE) 
     private Date completionDate; 
}