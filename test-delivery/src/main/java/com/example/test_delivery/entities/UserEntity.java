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

    @OneToMany
    private List<Order> orders;

    @OneToMany
    private List<Review> reviews;

    @OneToOne
    private UserCart cart;

    @ManyToMany
    private List<Notification> notifications;
}
