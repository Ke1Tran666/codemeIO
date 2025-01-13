package com.poly.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.bean.Payment;
import com.poly.bean.User;
import com.poly.service.PaymentService;
import com.poly.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        List<Payment> payments = paymentService.findAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Integer id) {
        Payment payment = paymentService.findById(id);
        return (payment != null) ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Payment>> findByStudentId(@PathVariable Integer studentId) {
        List<Payment> payments = paymentService.findByStudentId(studentId);
        if (payments == null || payments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {
        // Kiểm tra tính hợp lệ của đối tượng Payment
        if (payment == null) {
            return ResponseEntity.badRequest().body(null);
        }

        if (payment.getStudent() == null || payment.getStudent().getUserId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        User existingUser = userService.findById(payment.getStudent().getUserId());
        if (existingUser == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Kiểm tra xem Payment đã tồn tại với cùng studentId và courseId chưa
        boolean paymentExists = paymentService.existsByStudentIdAndCourseId(
            payment.getStudent().getUserId(), payment.getCourse().getCourseId()
        );
        
        if (paymentExists) {
            return ResponseEntity.status(200).body(null); // Trả về thành công nhưng không lưu
        }

        payment.setStudent(existingUser);
        Payment savedPayment = paymentService.save(payment);
        
        return ResponseEntity.status(201).body(savedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (!paymentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}