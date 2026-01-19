package com.example.test_delivery.repositories;

import com.example.test_delivery.entities.Dish;
import com.example.test_delivery.entities.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<UserCart, Long> {
    UserCart findByUserId(Long userId);
    //Dish findDishById(Long dishId);
}
