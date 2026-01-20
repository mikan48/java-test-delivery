package com.example.test_delivery.services;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.entities.Dish;
import com.example.test_delivery.entities.UserCart;
import com.example.test_delivery.entities.UserEntity;
import com.example.test_delivery.exeptions.ResourceNotFoundException;
import com.example.test_delivery.repositories.ICartRepository;
import com.example.test_delivery.repositories.IDishRepository;
import com.example.test_delivery.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ICartRepository cartRepository;
    private final IDishRepository dishRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    //to do
    public CartDto addInCart(Long userId, Long dishId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish Not Found; Dish id: " + dishId));

        UserCart cart = cartRepository.findByUserId(userId);
        List<Dish> dishes = cart.getDishes();
        dishes.add(dish);
        cart.setDishes(dishes);
        UserCart savedCart = cartRepository.save(cart);

        return modelMapper.map(savedCart, CartDto.class);
    }

    public CartDto deleteFromCart(Long userId, Long dishId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        dishRepository.findById(dishId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish Not Found; Dish id: " + dishId));

        UserCart cart = cartRepository.findByUserId(userId);
        List<Dish> dishes = cart.getDishes();

        if (dishes.removeIf(d -> Objects.equals(d.getId(), dishId))) {
            cart.setDishes(dishes);
            cartRepository.save(cart);
        }

        return modelMapper.map(cart, CartDto.class);
    }

    public CartDto getUserCart(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        UserCart cart = cartRepository.findByUserId(userId);

        return modelMapper.map(cart, CartDto.class);
    }

    public void clearCart(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        UserCart cart = cartRepository.findByUserId(userId);
        cart.setDishes(new ArrayList<>());
        cartRepository.save(cart);
    }
}
