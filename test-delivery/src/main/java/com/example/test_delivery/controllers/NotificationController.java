package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.NotificationDto;
import com.example.test_delivery.entities.NotificationStatus;
import com.example.test_delivery.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationDto notificationDto) {
        NotificationDto createdNotificationDto = notificationService.createNotification(notificationDto);
        return new ResponseEntity<>(createdNotificationDto, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<NotificationDto> userNotifications() {
//
//    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<NotificationDto> updateNotificationStatus(@PathVariable Long id, NotificationStatus status) {
        NotificationDto notificationDto = notificationService.updateNotificationStatus(id, status);
        return ResponseEntity.ok(notificationDto);
    }

}
