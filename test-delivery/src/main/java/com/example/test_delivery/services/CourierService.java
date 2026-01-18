package com.example.test_delivery.services;

import com.example.test_delivery.repositories.ICourierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final ICourierRepository reviewRepository;
    private final ModelMapper modelMapper;
}
