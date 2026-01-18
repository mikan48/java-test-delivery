package com.example.test_delivery.components;

import com.example.test_delivery.entities.Roles;
import com.example.test_delivery.entities.UserEntity;
import com.example.test_delivery.entities.UserStatus;
import com.example.test_delivery.repositories.IUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
//public class DbInit {
//    @Autowired
//    private IUserRepository userRepository;
//
//    @PostConstruct
//    private void SeedDb() {
//        UserEntity user = new UserEntity(
//                1L,
//                "Normal User 1",
//                Roles.USER,
//                UserStatus.ACTIVE);
//        userRepository.save(user);
//
//        UserEntity user1 = new UserEntity(
//                2L,
//                "Admin User 1",
//                Roles.ADMIN,
//                UserStatus.ACTIVE
//        );
//        userRepository.save(user1);
//    }
//}
