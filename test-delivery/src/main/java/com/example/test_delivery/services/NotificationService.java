package com.example.test_delivery.services;

import com.example.test_delivery.dto.NotificationDto;
import com.example.test_delivery.entities.Notification;
import com.example.test_delivery.entities.NotificationStatus;
import com.example.test_delivery.exeptions.ResourceNotFoundException;
import com.example.test_delivery.repositories.INotificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final INotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification newNotification = modelMapper.map(notificationDto, Notification.class);
        Notification savedNotification = notificationRepository.save(newNotification);

        return modelMapper.map(savedNotification, NotificationDto.class);
    }

    public NotificationDto updateNotificationStatus(Long notificationId, NotificationStatus status) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification Not found; Notification id: " + notificationId));
        notification.setStatus(status);
        Notification savedNotification = notificationRepository.save(notification);

        return modelMapper.map(savedNotification, NotificationDto.class);
    }
}
