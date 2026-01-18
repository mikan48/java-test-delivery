package com.example.test_delivery.services;

import com.example.test_delivery.repositories.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IOrderRepository reviewRepository;
    private final ModelMapper modelMapper;
}
