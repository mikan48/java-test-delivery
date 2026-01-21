package com.example.test_delivery.services;

import com.example.test_delivery.dto.CourierDto;
import com.example.test_delivery.dto.OrderDto;
import com.example.test_delivery.entities.Courier;
import com.example.test_delivery.entities.CourierStatus;
import com.example.test_delivery.entities.UserOrder;
import com.example.test_delivery.entities.OrderStatus;
import com.example.test_delivery.repositories.ICourierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final ICourierRepository courierRepository;
    private final ModelMapper modelMapper;

    public CourierDto registerCourier(CourierDto courierDto) {
        Courier newCourier = modelMapper.map(courierDto, Courier.class);
        Courier savedCourier = courierRepository.save(newCourier);

        return modelMapper.map(savedCourier, CourierDto.class);
    }

    public List<CourierDto> getCouriers() {
        return courierRepository.findAll()
                .stream().map(courier -> modelMapper.map(courier, CourierDto.class))
                .toList();
    }

    public List<OrderDto> getActiveCourierOrders(Long courierId) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Courier Not found; Courier id: " + courierId));
        ArrayList<UserOrder> activeOrders = new ArrayList<UserOrder>();

        if (courier.getUserOrders().isEmpty()) {
            return new ArrayList<>();
        }

        for (var order : courier.getUserOrders()) {
            if (order.getStatus() == OrderStatus.ACTIVE) {
                activeOrders.add(order);
            }
        }

        return activeOrders
                .stream().map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public CourierDto changeCourierStatus(Long courierId, CourierStatus status) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Courier Not found; Courier id: " + courierId));
        courier.setStatus(status);
        Courier savedCourier = courierRepository.save(courier);

        return modelMapper.map(savedCourier, CourierDto.class);
    }
}
