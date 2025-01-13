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

    @Temporal(TemporalType.DATE) 
    @Column(nullable=false) 
    private Date paymentDate; 

    @Column(nullable=false) 
    private boolean paymentStatus; // Trạng thái thanh toán (true: thành công, false: thất bại)

    @Temporal(TemporalType.DATE) 
    @Column(nullable=false) 
    private Date addedDate; // Ngày thêm mới
}