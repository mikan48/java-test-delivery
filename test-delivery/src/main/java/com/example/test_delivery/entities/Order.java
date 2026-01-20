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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Double totalCost;

    @ManyToOne
    private Courier courier;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToMany
    private List<Dish> dishes;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;
}
