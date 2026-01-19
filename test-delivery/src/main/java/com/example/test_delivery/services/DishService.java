package com.example.test_delivery.services;

import com.example.test_delivery.repositories.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {
    private final IDishRepository dishRepository;
    private final ModelMapper modelMapper;
}
