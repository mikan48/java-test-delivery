package com.example.test_delivery.configs;

import com.example.test_delivery.dto.DishDto;
import com.example.test_delivery.dto.ReviewDto;
import com.example.test_delivery.entities.Dish;
import com.example.test_delivery.entities.Review;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true);

        modelMapper.typeMap(Review.class, ReviewDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getRestaurant().getId(), ReviewDto::setRestaurantId);
                    mapper.map(src -> src.getUser().getId(), ReviewDto::setUserId);
                });
        modelMapper.typeMap(Dish.class, DishDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getRestaurant().getId(), DishDto::setRestaurantId);
                });

        return modelMapper;
    }
}
