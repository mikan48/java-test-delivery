package com.example.test_delivery.services;

import com.example.test_delivery.dto.DishDto;
import com.example.test_delivery.entities.Dish;
import com.example.test_delivery.entities.DishAvailability;
import com.example.test_delivery.repositories.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DishService {
    private final IDishRepository dishRepository;
    private final ModelMapper modelMapper;

    public DishDto updateDish(Long dishId, DishDto dishDto) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish Not Found; Dish id: " + dishId));
        modelMapper.map(dishDto, dish);
        Dish updatedDish = dishRepository.save(dish);

        return modelMapper.map(updatedDish, DishDto.class);
    }

    public void deleteDish(Long id) {
        dishRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish Not Found; Dish id: " + id));

        dishRepository.deleteById(id);
    }

    public DishDto updateDishAvailability(Long dishId, DishAvailability dishAvailability) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish Not Found; Dish id: " + dishId));
        dish.setDishAvailability(dishAvailability);
        dishRepository.save(dish);

        return modelMapper.map(dish, DishDto.class);
    }
}
