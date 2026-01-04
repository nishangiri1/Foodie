package com.foodie.food.services;

import com.foodie.food.dtos.FoodItemDTO;

import java.util.List;

public interface FoodItemService {

    FoodItemDTO createFoodItem(FoodItemDTO dto);

    FoodItemDTO getFoodItemById(String id);

    List<FoodItemDTO> getAllFoodItems();

    List<FoodItemDTO> getByRestaurant(String restaurantId);

    FoodItemDTO updateFoodItem(String id, FoodItemDTO dto);

    void deleteFoodItem(String id);
}
