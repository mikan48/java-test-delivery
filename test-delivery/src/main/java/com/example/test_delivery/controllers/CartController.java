package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.dto.UserDto;
import com.example.test_delivery.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

//    @PostMapping("/items")
//    public ResponseEntity<CartDto> addInCart() {
//
//    }
//
//    @DeleteMapping("/items/{id}")
//    public ResponseEntity<CartDto> deleteFromCart() {
//
//    }
//
//    @PutMapping("/items")
//    public ResponseEntity<CartDto> changeQuantity() {
//
//    }
//
//    @GetMapping
//    public ResponseEntity<CartDto> getCart() {
//
//    }
//
//    @DeleteMapping
//    public ResponseEntity<CartDto> clearCart() {
//
//    }
}
