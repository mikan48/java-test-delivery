package com.example.test_delivery.services;

import com.example.test_delivery.dto.DishDto;
import com.example.test_delivery.dto.RestaurantDto;
import com.example.test_delivery.entities.CuisineTypes;
import com.example.test_delivery.entities.Dish;
import com.example.test_delivery.entities.Restaurant;
import com.example.test_delivery.entities.RestaurantStatus;
import com.example.test_delivery.repositories.IDishRepository;
import com.example.test_delivery.repositories.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final IRestaurantRepository restaurantRepository;
    private final IDishRepository dishRepository;
    private final ModelMapper modelMapper;

    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }

    public RestaurantDto getRestaurantById(Long restaurantId) {
        Restaurant newRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found; Restaurant id: " + restaurantId));
        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);
        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }

    public List<RestaurantDto> getFilteredRestaurants(CuisineTypes cuisineType, Integer restaurantRating) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if (cuisineType != null) {
            restaurants = restaurants.stream().filter(r -> r.getCuisineType() == cuisineType).toList();
        }
        if (restaurantRating != null) {
            restaurants = restaurants.stream().filter(r -> r.getRating() != null && r.getRating() > restaurantRating).toList();
        }

        return restaurants
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    public RestaurantDto updateRestaurant(Long restaurantId, RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found; Restaurant id: " + restaurantId));
        modelMapper.map(restaurantDto, restaurant);
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        return modelMapper.map(updatedRestaurant, RestaurantDto.class);
    }

    public RestaurantDto closeRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found; Restaurant id: " + restaurantId));
        restaurant.setStatus(RestaurantStatus.CLOSED);
        restaurantRepository.save(restaurant);

        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    public DishDto addDishToMenu(Long restaurantId, DishDto dishDto) {
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found; Restaurant id: " + restaurantId));
        dish.setRestaurant(restaurant);
        Dish savedDish = dishRepository.save(dish);

        List<Dish> dishes = restaurant.getDishes();
        dishes.add(dish);
        restaurantRepository.save(restaurant);

        return modelMapper.map(savedDish, DishDto.class);
    }

    public List<DishDto> getRestaurantMenu(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found; Restaurant id: " + restaurantId));

        return restaurant.getDishes()
                .stream().map(dish -> modelMapper.map(dish, DishDto.class))
                .toList();
    }
}
