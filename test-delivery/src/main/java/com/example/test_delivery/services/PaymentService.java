package com.example.test_delivery.services;

import com.example.test_delivery.repositories.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final IPaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
}
