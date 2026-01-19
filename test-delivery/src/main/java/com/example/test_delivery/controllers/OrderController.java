package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.dto.OrderDto;
import com.example.test_delivery.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

//    @PostMapping
//    public ResponseEntity<OrderDto> createOrderFromCart() {
//        OrderDto orderDto = orderService.createOrderFromCart();
//        return ResponseEntity.ok(orderDto);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<OrderDto> getOrder() {
//
//    }
//
//    @GetMapping
//    public ResponseEntity<OrderDto> getUserOrders() {
//
//    }
//
//    @PatchMapping
//    public ResponseEntity<OrderDto> changeStatus() {
//
//    }
//
//    @DeleteMapping
//    public ResponseEntity<OrderDto> cancelOrder() {
//
//    }
//
//    @GetMapping
//    public ResponseEntity<OrderDto> filterOrdersByStatus() {
//
//    }
}
