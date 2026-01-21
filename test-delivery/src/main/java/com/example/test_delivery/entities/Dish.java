package com.example.test_delivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double cost;

    @Enumerated(EnumType.STRING)
    private DishAvailability dishAvailability;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Restaurant restaurant;

    @ManyToMany
    private List<UserOrder> userOrders;

    @ManyToMany
    private List<UserCart> cart;
}
