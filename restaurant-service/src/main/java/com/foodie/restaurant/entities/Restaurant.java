package com.foodie.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class Restaurant {
    @Id
    private String id;

    private String name;

    private String address;

    private String phone;

    @ElementCollection
    private List<String> picture=new ArrayList<>();

    private boolean open=false;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    @Lob
    private String aboutRestaurant;
}
