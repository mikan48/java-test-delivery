package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.PaymentDto;
import com.example.test_delivery.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

//    @PostMapping
//    public ResponseEntity<PaymentDto> addPayment() {
//
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PaymentDto> getPaymentStatus() {
//
//    }
//
//    @PatchMapping("/{id}/status")
//    public ResponseEntity<PaymentDto> updatePaymentStatus() {
//
//    }
//
//    @GetMapping
//    public ResponseEntity<PaymentDto> orderPayments() {
//
//    }
}
