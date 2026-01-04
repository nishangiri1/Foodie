package com.foodie.food.services.external;


import com.foodie.food.dtos.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "restaurant-service",url = "http://localhost:9091")
public interface RestaurantService {

    @GetMapping("/api/v1/restaurants/{restaurantId}")
    RestaurantDto getById(@PathVariable String restaurantId);

    @GetMapping("/api/v1/restaurants")
    RestaurantDto getAll( String restaurantId);

    @PostMapping("/api/v1/restaurants")
    RestaurantDto create(@RequestBody RestaurantDto restaurantDto);

    @DeleteMapping("/api/v1/restaurants/{restaurantId}")
    RestaurantDto delete(@PathVariable String restaurantId);

}
