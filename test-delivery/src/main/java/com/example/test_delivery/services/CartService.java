package com.example.test_delivery.services;

import com.example.test_delivery.repositories.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ICartRepository cartRepository;
    private final ModelMapper modelMapper;
}
