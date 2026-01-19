package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.DishDto;
import com.example.test_delivery.dto.RestaurantDto;
import com.example.test_delivery.entities.CuisineTypes;
import com.example.test_delivery.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurants(
            @RequestParam(required = false)CuisineTypes cuisineType,
            @RequestParam(required = false)Integer restaurantRating) {
        List<RestaurantDto> restaurants = restaurantService.getFilteredRestaurants(cuisineType, restaurantRating);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable Long id) {
        RestaurantDto restaurantDto = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurantDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto) {
        RestaurantDto updatedRestaurant = restaurantService.updateRestaurant(id, restaurantDto);
        return ResponseEntity.ok(restaurantDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestaurantDto> closeRestaurant(@PathVariable Long id) {
        RestaurantDto restaurantDto = restaurantService.closeRestaurant(id);
        return ResponseEntity.ok(restaurantDto);
    }

    @PostMapping("/{id}/menu")
    public ResponseEntity<DishDto> addDishToMenu(@PathVariable Long id, @RequestBody DishDto dishDto) {
        DishDto addedDish = restaurantService.addDishToMenu(id, dishDto);
        return ResponseEntity.ok(addedDish);
    }

    @GetMapping("/{id}/menu")
    public ResponseEntity<List<DishDto>> getRestaurantMenu(@PathVariable Long id) {
        List<DishDto> dishDtos = restaurantService.getRestaurantMenu(id);
        return ResponseEntity.ok(dishDtos);
    }

}
