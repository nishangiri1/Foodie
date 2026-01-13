package com.foodie.food.services.external;


import com.foodie.food.config.AppConstant;
import com.foodie.food.dtos.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = AppConstant.RESTAURANT_SERVICE_NAME,fallback = RestaurantServiceFallback.class)
public interface RestaurantService {

    @GetMapping("restaurants/api/v1/restaurants/{restaurantId}")
    RestaurantDto getById(@PathVariable String restaurantId);

    @GetMapping("restaurants/api/v1/restaurants")
    RestaurantDto getAll( String restaurantId);

    @PostMapping("restaurants/api/v1/restaurants")
    RestaurantDto create(@RequestBody RestaurantDto restaurantDto);

    @DeleteMapping("restaurants/api/v1/restaurants/{restaurantId}")
    RestaurantDto delete(@PathVariable String restaurantId);

}
