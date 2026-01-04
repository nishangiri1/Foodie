package com.foodie.food.controllers;

import com.foodie.food.dtos.FoodItemDTO;
import com.foodie.food.services.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food-items")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    public FoodItemDTO create(@RequestBody FoodItemDTO dto) {
        return foodItemService.createFoodItem(dto);
    }

    @GetMapping("/{id}")
    public FoodItemDTO get(@PathVariable String id) {
        return foodItemService.getFoodItemById(id);
    }

    @GetMapping
    public List<FoodItemDTO> getAll() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodItemDTO> getByRestaurant(@PathVariable String restaurantId) {
        return foodItemService.getByRestaurant(restaurantId);
    }

    @PutMapping("/{id}")
    public FoodItemDTO update(@PathVariable String id,
                              @RequestBody FoodItemDTO dto) {
        return foodItemService.updateFoodItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
    }
}
