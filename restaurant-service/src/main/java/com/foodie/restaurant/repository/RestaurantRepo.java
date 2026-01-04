package com.foodie.restaurant.repository;

import com.foodie.restaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<Restaurant,String> {

    Optional<Restaurant> findByNameContainingIgnoreCase(String name);


}
