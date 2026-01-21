package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.DishDto;
import com.example.test_delivery.entities.Dish;
import com.example.test_delivery.entities.DishAvailability;
import com.example.test_delivery.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final DishService dishService;

    @PutMapping("/{id}")
    public ResponseEntity<DishDto> updateDish(@PathVariable Long id, @RequestBody DishDto dishDto) {
        DishDto updatedDish = dishService.updateDish(id, dishDto);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<DishDto> updateDishAvailability(@PathVariable Long id, DishAvailability dishAvailability) {
        DishDto updatedDish = dishService.updateDishAvailability(id, dishAvailability);
        return ResponseEntity.ok(updatedDish);
    }

}
