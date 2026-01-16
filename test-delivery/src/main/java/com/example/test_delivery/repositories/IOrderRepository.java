package com.example.test_delivery.repositories;

import com.example.test_delivery.entities.Order;
import com.example.test_delivery.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
