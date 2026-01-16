package com.example.test_delivery.repositories;

import com.example.test_delivery.entities.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourierRepository extends JpaRepository<Courier, Long> {
}
