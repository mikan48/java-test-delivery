package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.dto.OrderDto;
import com.example.test_delivery.entities.OrderStatus;
import com.example.test_delivery.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrderFromCart(Long userId) {
        OrderDto orderDto = orderService.createOrderFromCart(userId);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        OrderDto order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getUserOrders(@RequestParam Long userId) {
        List<OrderDto> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }

//    @PatchMapping("{id}/status")
//    public ResponseEntity<OrderDto> changeStatus() {
//
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long id) {
        OrderDto orderDto = orderService.cancelOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<OrderDto>> filterOrdersByStatus(@RequestParam(required = false) OrderStatus orderStatus) {
        List<OrderDto> orders = orderService.filterOrdersByStatus(orderStatus);
        return ResponseEntity.ok(orders);
    }
}
