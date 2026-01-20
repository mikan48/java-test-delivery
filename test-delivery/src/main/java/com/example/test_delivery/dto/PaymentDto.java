package com.example.test_delivery.dto;

import com.example.test_delivery.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private PaymentStatus status;
    //private Long userId;
    private Long orderId;
}
