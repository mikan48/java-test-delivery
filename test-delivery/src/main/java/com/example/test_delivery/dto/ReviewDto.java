package com.example.test_delivery.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;
    private String text;
    private Long userId;
    private Long restaurantId;
}
