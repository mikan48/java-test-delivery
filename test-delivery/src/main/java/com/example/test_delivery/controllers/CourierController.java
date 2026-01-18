package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.dto.CourierDto;
import com.example.test_delivery.services.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

//    @PostMapping
//    public ResponseEntity<CourierDto> registerCourier() {
//
//    }
//
//    @GetMapping
//    public ResponseEntity<CourierDto> courierList() {
//
//    }
//
//    @PatchMapping("/{id}/assign")
//    public ResponseEntity<CourierDto> assignOrderForCourier() {
//
//    }
//
//    @GetMapping("/{id}/orders")
//    public ResponseEntity<CourierDto> activeCourierOrders() {
//
//    }
//
//    @PatchMapping("/{id}/status")
//    public ResponseEntity<CourierDto> courierStatus() {
//
//    }
}
