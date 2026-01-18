package com.example.test_delivery.services;

import com.example.test_delivery.dto.ReviewDto;
import com.example.test_delivery.entities.Restaurant;
import com.example.test_delivery.entities.Review;
import com.example.test_delivery.entities.UserEntity;
import com.example.test_delivery.exeptions.ResourceNotFoundException;
import com.example.test_delivery.repositories.IRestaurantRepository;
import com.example.test_delivery.repositories.IReviewRepository;
import com.example.test_delivery.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final IReviewRepository reviewRepository;
    private final IUserRepository userRepository;
    private final IRestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public ReviewDto addReview(ReviewDto reviewDto) {
        Review newReview = modelMapper.map(reviewDto, Review.class);

        UserEntity user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found; User id: " + reviewDto.getUserId()));
        Restaurant restaurant = restaurantRepository.findById(reviewDto.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found; Restaurant id: " + reviewDto.getRestaurantId()));

        newReview.setUser(user);
        newReview.setRestaurant(restaurant);
        Review savedReview = reviewRepository.save(newReview);

        List<Review> userReviews = user.getReviews();
        userReviews.add(newReview);
        user.setReviews(userReviews);
        userRepository.save(user);

        List<Review> restaurantReviews = restaurant.getReviews();
        restaurantReviews.add(newReview);
        restaurant.setReviews(restaurantReviews);
        restaurantRepository.save(restaurant);

        ReviewDto newReviewDto = modelMapper.map(savedReview, ReviewDto.class);
        newReviewDto.setUserId(reviewDto.getUserId());
        newReviewDto.setRestaurantId(reviewDto.getRestaurantId());

        return newReviewDto;
    }
}
