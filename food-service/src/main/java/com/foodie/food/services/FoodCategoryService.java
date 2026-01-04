package com.foodie.food.services;

import com.foodie.food.dtos.FoodCategoryDTO;

import java.util.List;

public interface FoodCategoryService {

    FoodCategoryDTO createFoodCategory(FoodCategoryDTO dto);

    FoodCategoryDTO getFoodCategoryById(String id);

    List<FoodCategoryDTO> getAllFoodCategory();

    FoodCategoryDTO updateFoodCategory(String id, FoodCategoryDTO dto);

    void deleteFoodCategory(String id);
}
