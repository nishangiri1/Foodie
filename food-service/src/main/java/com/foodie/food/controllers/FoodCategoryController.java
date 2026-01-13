package com.foodie.food.controllers;

import com.foodie.food.dtos.FoodCategoryDTO;
import com.foodie.food.services.FoodCategoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foods/api/v1/food-categories")
@RequiredArgsConstructor
class FoodCategoryController {

    private final FoodCategoryService foodCategoryServiceservice;

    @PostMapping
    public FoodCategoryDTO createFoodCategory(@RequestBody FoodCategoryDTO dto) {
        return foodCategoryServiceservice.createFoodCategory(dto);
    }

    @GetMapping("/{id}")
    public FoodCategoryDTO getFoodCategoryById(@PathVariable String id) {
        return foodCategoryServiceservice.getFoodCategoryById(id);
    }

    @GetMapping
    public List<FoodCategoryDTO> getAllFoodCategories() {
        return foodCategoryServiceservice.getAllFoodCategory();
    }

    @PutMapping("/{id}")
    public FoodCategoryDTO updateFoodCategory(@PathVariable String id,
                                  @RequestBody FoodCategoryDTO dto) {
        return foodCategoryServiceservice.updateFoodCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        foodCategoryServiceservice.deleteFoodCategory(id);
    }
}
