package com.example.test_delivery.controllers;

import com.example.test_delivery.dto.ReviewDto;
import com.example.test_delivery.dto.UserDto;
import com.example.test_delivery.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto review = reviewService.addReview(reviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
}
