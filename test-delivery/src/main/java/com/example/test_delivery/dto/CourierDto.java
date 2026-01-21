package com.example.test_delivery.dto;

import com.example.test_delivery.entities.CourierStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierDto {
    private String name;
    private CourierStatus status;
}
