package com.example.test_delivery.components;

import com.example.test_delivery.entities.*;
import com.example.test_delivery.repositories.ICourierRepository;
import com.example.test_delivery.repositories.IRestaurantRepository;
import com.example.test_delivery.repositories.IUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DbInit {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRestaurantRepository restaurantRepository;
    @Autowired
    private ICourierRepository courierRepository;

    @PostConstruct
    private void SeedDb() {
        //users
        UserEntity user = new UserEntity();
        user.setName("Normal User 1");
        user.setRole(Roles.USER);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        UserEntity user1 = new UserEntity();
        user1.setName("Admin User 1");
        user1.setRole(Roles.ADMIN);
        user1.setStatus(UserStatus.ACTIVE);
        userRepository.save(user1);

        //restaurants
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Some REST");
        restaurant.setStatus(RestaurantStatus.OPEN);
        restaurant.setAdress("Somwhere");
        restaurantRepository.save(restaurant);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Спекся");
        restaurant1.setStatus(RestaurantStatus.CLOSED);
        restaurant1.setAdress("Somwhere 13/1");
        restaurantRepository.save(restaurant1);

        //couriers
        Courier courier = new Courier();
        courier.setName("Courier N1");
        courier.setStatus(CourierStatus.ONLINE);
        courierRepository.save(courier);
    }
}
