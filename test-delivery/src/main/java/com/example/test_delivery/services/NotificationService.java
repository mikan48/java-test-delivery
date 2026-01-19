package com.example.test_delivery.services;

import com.example.test_delivery.repositories.INotificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final INotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
}
