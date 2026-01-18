package com.example.test_delivery.services;

import com.example.test_delivery.dto.RestaurantDto;
import com.example.test_delivery.entities.Restaurant;
import com.example.test_delivery.exeptions.ResourceNotFoundException;
import com.example.test_delivery.repositories.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final IRestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }

    public RestaurantDto getRestaurantById(Long restaurantId) {
        Restaurant newRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found; Restaurant id: " + restaurantId));
        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);
        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }
}
