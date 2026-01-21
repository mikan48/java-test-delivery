package com.example.test_delivery.services;

import com.example.test_delivery.dto.CartDto;
import com.example.test_delivery.dto.DishDto;
import com.example.test_delivery.entities.Dish;
import com.example.test_delivery.entities.UserCart;
import com.example.test_delivery.entities.UserEntity;
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

        UserCart cart = user.getCart();
        if(cart == null) {
            cart = new UserCart();
            cart.setUser(user);
        }

        List<Dish> dishes = new ArrayList<>();
        if (cart.getDishes() != null) {
            dishes = cart.getDishes();
        }
        dishes.add(dish);
        cart.setDishes(dishes);
        UserCart savedCart = cartRepository.save(cart);

        user.setCart(cart);
        userRepository.save(user);

        CartDto finalCart = modelMapper.map(savedCart, CartDto.class);
        List<DishDto> mappedDishes = Objects.requireNonNull(dishes)
                .stream().map(d -> modelMapper.map(d, DishDto.class)).toList();
        finalCart.setDishes(mappedDishes);

        return finalCart;
    }

    public CartDto deleteFromCart(Long userId, Long dishId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        dishRepository.findById(dishId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish Not Found; Dish id: " + dishId));

        UserCart cart = user.getCart();
        if (cart != null) {
            List<Dish> dishes = cart.getDishes();

            if (dishes.removeIf(d -> Objects.equals(d.getId(), dishId))) {
                cart.setDishes(dishes);
                cartRepository.save(cart);
            }
        }
        else {
            cart = new UserCart();
            cart.setUser(user);
        }

        return modelMapper.map(cart, CartDto.class);
    }

    public CartDto getUserCart(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        UserCart cart = user.getCart();
        if(cart == null) {
            cart = new UserCart();
            cart.setUser(user);
        }

        List<Dish> dishes = new ArrayList<>();
        if (cart.getDishes() != null) {
            dishes = cart.getDishes();
        }

        CartDto finalCart = modelMapper.map(cart, CartDto.class);
        List<DishDto> mappedDishes = Objects.requireNonNull(dishes)
                .stream().map(d -> modelMapper.map(d, DishDto.class)).toList();
        finalCart.setDishes(mappedDishes);

        return finalCart;
    }

    public void clearCart(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found; User id: " + userId));
        UserCart cart = user.getCart();
        if(cart == null) {
            cart = new UserCart();
            cart.setUser(user);
        }
        cart.setDishes(new ArrayList<>());
        cartRepository.save(cart);

        user.setCart(cart);
        userRepository.save(user);
    }
}
