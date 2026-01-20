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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found; User id: " + reviewDto.getUserId()));
        Restaurant restaurant = restaurantRepository.findById(reviewDto.getRestaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found; Restaurant id: " + reviewDto.getRestaurantId()));

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

    public List<ReviewDto> getRestaurantReviews(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found; Restaurant id: " + restaurantId));

        return restaurant.getReviews()
                .stream()
                .map(review -> modelMapper.map(review, ReviewDto.class)).toList();

    }

    public ReviewDto updateReview(Long reviewId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found; Review id: " + reviewId));
        modelMapper.map(reviewDto, review);
        Review updatedReview = reviewRepository.save(review);

        return modelMapper.map(updatedReview, ReviewDto.class);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found; Review id: " + reviewId));
        userRepository.deleteById(reviewId);
    }
}
