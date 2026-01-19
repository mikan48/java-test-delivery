package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.dto.DishDto;
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

    //to do

    @PostMapping("/items")
    public ResponseEntity<CartDto> addInCart(Long userId, Long dishId) {
        CartDto updatedCart = cartService.addInCart(userId, dishId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<CartDto> deleteFromCart(@PathVariable Long dishId, Long userId) {
        CartDto updatedCart = cartService.deleteFromCart(userId, dishId);
        return ResponseEntity.ok(updatedCart);
    }

//    @PutMapping("/items")
//    public ResponseEntity<CartDto> changeQuantity() {
//
//    }
//
    @GetMapping
    public ResponseEntity<CartDto> getCart(Long userId) {
        CartDto cart = cartService.getUserCart(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
