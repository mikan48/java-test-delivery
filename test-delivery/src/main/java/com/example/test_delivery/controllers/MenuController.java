package com.example.test_delivery.controllers;

import com.example.test_delivery.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final DishService dishService;


//    @PutMapping("/menu/{id}")
//    public ResponseEntity<RestaurantDto> updateDish() {
//
//    }
//
//    @DeleteMapping("/menu/{id}")
//    public ResponseEntity<RestaurantDto> deleteDish() {
//
//    }
//
//    @PatchMapping("/menu/{id}/availability ")
//    public ResponseEntity<RestaurantDto> updateDish() {
//
//    }

}
