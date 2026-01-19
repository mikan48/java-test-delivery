package com.example.test_delivery.repositories;

import com.example.test_delivery.entities.Roles;
import com.example.test_delivery.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByRole(Roles role);
}
