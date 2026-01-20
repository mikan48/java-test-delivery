package com.example.test_delivery.services;

import com.example.test_delivery.dto.OrderDto;
import com.example.test_delivery.dto.UserDto;
import com.example.test_delivery.entities.Order;
import com.example.test_delivery.entities.OrderStatus;
import com.example.test_delivery.entities.UserCart;
import com.example.test_delivery.entities.UserEntity;
import com.example.test_delivery.exeptions.ResourceNotFoundException;
import com.example.test_delivery.repositories.ICartRepository;
import com.example.test_delivery.repositories.IOrderRepository;
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
public class OrderService {
    private final IOrderRepository orderRepository;
    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    public OrderDto createOrderFromCart(Long userId) {
        UserCart cart = cartRepository.findByUserId(userId);
        Order order = new Order();
        order.setDishes(cart.getDishes());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setUserEntity(cart.getUser());
        order.setTotalCost(cart.getTotalCost());
        Order savedOrder = orderRepository.save(order);
        cart.setDishes(new ArrayList<>());
        cartRepository.save(cart);

        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found; Order id: " + orderId));
        return modelMapper.map(order, OrderDto.class);
    }

    public List<OrderDto> getUserOrders(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found; User id: " + userId));
        List<Order> orders = user.getOrders();

        return orders
                .stream().map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public OrderDto cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found; Order id: " + orderId));
        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);

        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public List<OrderDto> filterOrdersByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.findAll();
        if (status != null) {
            orders = orders.stream().filter(o -> o.getStatus().equals(status))
                    .toList();
        }

        return orders
                .stream().map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }
}
