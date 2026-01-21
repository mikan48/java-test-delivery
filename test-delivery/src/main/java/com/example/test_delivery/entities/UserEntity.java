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
//@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(unique = true)
    //private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Builder.Default
    @OneToMany
    private List<UserOrder> userOrders = new ArrayList<>();

    @Builder.Default
    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    @OneToOne
    private UserCart cart;

    @Builder.Default
    @ManyToMany
    private List<Notification> notifications = new ArrayList<>();

}
