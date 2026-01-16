package com.example.test_delivery.controllers;

import com.example.test_delivery.entities.UserEntity;
import com.example.test_delivery.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<> registerUser() {
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<> getUser() {
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<> updateUser() {
//    }
//
//    @GetMapping
//    public ResponseEntity<> getUserByRole() {
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<> deleteUser() {
//    }

}
