package com.poly.bean; 

import java.io.Serializable; 
import java.math.BigDecimal; 
import java.util.Date; 

import jakarta.persistence.*; 
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor; 

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity 
@Table(name="Payments") 
public class Payment implements Serializable { 
     @Id 
     @GeneratedValue(strategy=GenerationType.IDENTITY) 
     private Integer paymentId; 

     @ManyToOne 
     @JoinColumn(name="StudentId") 
     private User student; // Liên kết đến User (học viên) 

     @ManyToOne 
     @JoinColumn(name="CourseId") 
     private Course course; // Liên kết đến Course 

     @Column(nullable=false) 
     private BigDecimal amount; 

     @Temporal(TemporalType.DATE) 
     @Column(nullable=false) 
     private Date paymentDate; 

     @Column(nullable=false) 
     private String paymentStatus; // Trạng thái thanh toán (thành công, thất bại, v.v.)
}