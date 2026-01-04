package com.foodie.food.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodCategoryDTO {

    private String id;
    private String name;
    private String description;

    // Optional: include items if needed
    private List<FoodItemDTO> foodItems;
}

