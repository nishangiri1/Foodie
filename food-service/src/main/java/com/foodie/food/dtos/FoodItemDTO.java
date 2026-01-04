package com.foodie.food.dtos;

import com.foodie.food.entities.FoodType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

    private String id;
    private String restaurantId;
    private String title;
    private String description;
    private int quantity;
    private boolean outOfStock;
    private FoodType foodType;
    private FoodCategoryDTO foodCategory;
    private RestaurantDto restaurant;

    private String foodCategoryId;
}

