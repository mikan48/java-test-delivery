package com.example.test_delivery.dto;

import com.example.test_delivery.entities.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private String text;
    private NotificationStatus status;
}
