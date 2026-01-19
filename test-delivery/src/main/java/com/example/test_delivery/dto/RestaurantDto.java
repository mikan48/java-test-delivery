package com.example.test_delivery.dto;

import com.example.test_delivery.entities.CuisineTypes;
import com.example.test_delivery.entities.RestaurantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private String name;
    private String adress;
    private RestaurantStatus status;
    private CuisineTypes cuisineType;
    private Double rating;
}
