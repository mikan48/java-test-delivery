package com.example.test_delivery.dto;

import com.example.test_delivery.entities.DishAvailability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    private String name;
    private Double cost;
    private DishAvailability dishAvailability;
    private Long restaurantId;
}
