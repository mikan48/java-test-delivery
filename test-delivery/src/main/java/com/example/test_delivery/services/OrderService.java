package com.example.test_delivery.services;

import com.example.test_delivery.dto.DishDto;
import com.example.test_delivery.dto.OrderDto;
import com.example.test_delivery.entities.*;
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
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));

        UserCart cart = user.getCart();
        if(cart == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(cart.getDishes().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty");
        }

        if(cart.getTotalCost() < 300D) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create userOrder, total cost < 300");
        }

        UserOrder userOrder = new UserOrder();
        List<Dish> dishes = new ArrayList<>(cart.getDishes());
        userOrder.setDishes(dishes);
        userOrder.setStatus(OrderStatus.NOT_PAYED);
        userOrder.setUserEntity(cart.getUser());
        userOrder.setTotalCost(cart.getTotalCost());
        UserOrder savedOrder = orderRepository.save(userOrder);

        List<UserOrder> orders = user.getUserOrders();
        orders.add(userOrder);
        userRepository.save(user);

        cart.setDishes(new ArrayList<>());
        cartRepository.save(cart);

        OrderDto finalOrder = modelMapper.map(savedOrder, OrderDto.class);
        finalOrder.setDishes(savedOrder.getDishes()
                .stream().map(d -> modelMapper.map(d, DishDto.class))
                .toList());

        return finalOrder;
    }

    public OrderDto getOrder(Long orderId) {
        UserOrder userOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserOrder Not Found; UserOrder id: " + orderId));
        return modelMapper.map(userOrder, OrderDto.class);
    }

    public List<OrderDto> getUserOrders(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found; User id: " + userId));
        List<UserOrder> orders = user.getUserOrders();

        return orders
                .stream().map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public OrderDto cancelOrder(Long orderId) {
        UserOrder userOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserOrder Not Found; UserOrder id: " + orderId));
        userOrder.setStatus(OrderStatus.CANCELLED);
        UserOrder savedOrder = orderRepository.save(userOrder);

        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public List<OrderDto> filterOrdersByStatus(OrderStatus status) {
        List<UserOrder> orders = orderRepository.findAll();
        if (status != null) {
            orders = orders.stream().filter(o -> o.getStatus().equals(status))
                    .toList();
        }

        return orders
                .stream().map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }
}
