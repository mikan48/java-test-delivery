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
public class UserOrder {
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

    @Builder.Default
    @ManyToMany
    private List<Dish> dishes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "userOrder")
    private List<Payment> payments = new ArrayList<>();
}
