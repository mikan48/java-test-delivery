package com.example.test_delivery.repositories;

import com.example.test_delivery.entities.Restaurant;
import com.example.test_delivery.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
}
