package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.RestaurantDto;
import com.example.test_delivery.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        RestaurantDto createdRestaurant = restaurantService.addRestaurant(restaurantDto);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<RestaurantDto> getRestaurants() {
//
//    }
//
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable Long id) {
        RestaurantDto restaurantDto = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurantDto);
    }
//
//    @PutMapping
//    public ResponseEntity<RestaurantDto> updateRestaurant() {
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RestaurantDto> closeRestaurant() {
//
//    }
//
//    @PostMapping("/{id}/menu")
//    public ResponseEntity<RestaurantDto> addDish() {
//
//    }
//
//    @GetMapping("/{id}/menu")
//    public ResponseEntity<RestaurantDto> getMenu() {
//
//    }

}
