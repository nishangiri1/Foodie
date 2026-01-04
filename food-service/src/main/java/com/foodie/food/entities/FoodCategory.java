package com.foodie.food.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "food_service_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategory {
    @Id
    private String id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "foodCategory",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodItem> foodItems=new ArrayList<>();
}
