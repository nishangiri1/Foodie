package com.foodie.food.repositories;

import com.foodie.food.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,String> {

    List<FoodItem> findByRestaurantId(String restaurantId);

}
