package com.example.test_delivery.components;

import com.example.test_delivery.entities.*;
import com.example.test_delivery.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRestaurantRepository restaurantRepository;
    @Autowired
    private ICourierRepository courierRepository;
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private IPaymentRepository paymentRepository;
    @Autowired
    private IDishRepository dishRepository;
    @Autowired
    private IOrderRepository orderRepository;

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
        restaurant.setCuisineType(CuisineTypes.ASIAN);
        restaurant.setAdress("Somwhere");
        restaurantRepository.save(restaurant);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Pizza House");
        restaurant2.setStatus(RestaurantStatus.OPEN);
        restaurant2.setCuisineType(CuisineTypes.PIZZA);
        restaurant2.setAdress("Yorkville Village");
        restaurantRepository.save(restaurant2);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Спекся");
        restaurant1.setStatus(RestaurantStatus.CLOSED);
        restaurant1.setCuisineType(CuisineTypes.BURGERS);
        restaurant1.setAdress("Somwhere 13/1");
        restaurantRepository.save(restaurant1);

        //menu
        Dish dish = new Dish();
        dish.setRestaurant(restaurant);
        dish.setDishAvailability(DishAvailability.AVAILABLE);
        dish.setCost(30.2);
        dish.setName("Dish 1.1");
        dishRepository.save(dish);

        //reviews
//        Review review = new Review();
//        review.setUser(user);
//        review.setRestaurant(restaurant);
//        review.setText("Terrible");
//        review.setRating(1);
//        reviewRepository.save(review);
//
//        Review review1 = new Review();
//        review1.setUser(user1);
//        review1.setRestaurant(restaurant);
//        review1.setText("So-so");
//        review1.setRating(3);
//        reviewRepository.save(review1);

        //couriers
        Courier courier = new Courier();
        courier.setName("Courier N1");
        courier.setStatus(CourierStatus.ONLINE);
        courierRepository.save(courier);

        //orders
//        Order order = new Order();
//        order.setUserEntity(user);
//        var dishes = new ArrayList<Dish>();
//        dishes.add(dish);
//        order.setDishes(dishes);
//        order.setStatus(OrderStatus.CANCELLED);
//        orderRepository.save(order);

        //payments
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.FAILED);
        //payment.setUserEntity(user);
        //payment.setOrder(order);
        paymentRepository.save(payment);
    }
}
