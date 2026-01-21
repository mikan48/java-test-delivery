package com.example.test_delivery.services;

import com.example.test_delivery.dto.PaymentDto;
import com.example.test_delivery.entities.UserEntity;
import com.example.test_delivery.entities.UserOrder;
import com.example.test_delivery.entities.Payment;
import com.example.test_delivery.entities.PaymentStatus;
import com.example.test_delivery.repositories.IOrderRepository;
import com.example.test_delivery.repositories.IPaymentRepository;
import com.example.test_delivery.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final IPaymentRepository paymentRepository;
    private final IUserRepository userRepository;
    private final IOrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public PaymentDto addPayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        UserEntity user = userRepository.findById(paymentDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found; User id: " + paymentDto.getUserId()));
        UserOrder userOrder = orderRepository.findById(paymentDto.getOrderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserOrder Not Found; UserOrder id: " + paymentDto.getOrderId()));

        payment.setUserEntity(user);
        payment.setUserOrder(userOrder);
        Payment newPayment = paymentRepository.save(payment);

        List<Payment> userPayments = new ArrayList<>(user.getPayments());
        userPayments.add(payment);
        user.setPayments(userPayments);
        userRepository.save(user);

        List<Payment> orderPayments = new ArrayList<>(userOrder.getPayments());
        orderPayments.add(payment);
        userOrder.setPayments(orderPayments);
        orderRepository.save(userOrder);

        return modelMapper.map(newPayment, PaymentDto.class);
    }

    public PaymentStatus getPaymentStatus(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment Not Found; Payment id: " + paymentId));
        return payment.getStatus();
    }

    public PaymentDto updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment Not Found; Payment id: " + paymentId));
        payment.setStatus(status);
        Payment savedPayment = paymentRepository.save(payment);

        return modelMapper.map(savedPayment, PaymentDto.class);
    }

    public List<PaymentDto> orderPayments(Long orderId) {
        UserOrder userOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserOrder Not Found; UserOrder id: " + orderId));

        return userOrder.getPayments()
                .stream().map(payment -> modelMapper.map(payment, PaymentDto.class))
                .toList();
    }
}
