package com.example.test_delivery.repositories;

import com.example.test_delivery.entities.PushUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPushUpRepository extends JpaRepository<PushUp, Long> {
}
