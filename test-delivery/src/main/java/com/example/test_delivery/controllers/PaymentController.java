package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.PaymentDto;
import com.example.test_delivery.entities.PaymentStatus;
import com.example.test_delivery.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> addPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto newPayment = paymentService.addPayment(paymentDto);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentStatus> getPaymentStatus(@PathVariable Long id) {
        PaymentStatus status = paymentService.getPaymentStatus(id);
        return ResponseEntity.ok(status);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PaymentDto> updatePaymentStatus(@PathVariable Long id, PaymentStatus paymentStatus) {
        PaymentDto paymentDto = paymentService.updatePaymentStatus(id, paymentStatus);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> orderPayments(@RequestParam Long orderId) {
        List<PaymentDto> payments = paymentService.orderPayments(orderId);
        return ResponseEntity.ok(payments);
    }
}
