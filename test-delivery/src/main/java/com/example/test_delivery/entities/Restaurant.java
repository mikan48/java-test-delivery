package com.example.test_delivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String adress;
    private Double rating;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Enumerated(EnumType.STRING)
    private CuisineTypes cuisineType;

    @OneToMany
    private List<Dish> dishes;

    @OneToMany
    private List<Review> reviews;

    public void setReviews(List<Review> newReviews) {
        reviews = newReviews;

        if (!newReviews.isEmpty()) {
            Double ratingSum = 0D;
            for(var review : reviews) {
                ratingSum += review.getRating();
            }

            rating = ratingSum / reviews.size();
        }
    }
}
