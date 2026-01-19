package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.dto.CourierDto;
import com.example.test_delivery.dto.OrderDto;
import com.example.test_delivery.entities.CourierStatus;
import com.example.test_delivery.services.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<CourierDto> registerCourier(@RequestBody CourierDto courierDto) {
        CourierDto registeredCourier = courierService.registerCourier(courierDto);
        return new ResponseEntity<>(registeredCourier, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourierDto>> courierList() {
        List<CourierDto> couriers = courierService.getCouriers();
        return ResponseEntity.ok(couriers);
    }

//    @PatchMapping("/{id}/assign")
//    public ResponseEntity<CourierDto> assignOrderForCourier() {
//
//    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderDto>> activeCourierOrders(@PathVariable Long id) {
        List<OrderDto> orders = courierService.getActiveCourierOrders(id);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CourierDto> changeCourierStatus(@PathVariable Long id, CourierStatus status) {
        CourierDto courierDto = courierService.changeCourierStatus(id, status);
        return ResponseEntity.ok(courierDto);
    }

}
