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
public class UserCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalCost;
    @OneToOne
    private UserEntity user;

    @OneToMany
    private List<Dish> dishes;

    public void setDishes(List<Dish> newDishes) {
        dishes = newDishes;

        Double sumCost = 0D;
        for (var dish : dishes) {
            sumCost += dish.getCost();
        }

        totalCost = sumCost;
    }
}
