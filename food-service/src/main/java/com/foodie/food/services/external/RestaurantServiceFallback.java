package com.foodie.food.services.external;

import com.foodie.food.dtos.RestaurantDto;
import org.springframework.stereotype.Component;


@Component
public class RestaurantServiceFallback implements RestaurantService{

    @Override
    public RestaurantDto getById(String restaurantId) {

        return null;
    }

    @Override
    public RestaurantDto getAll(String restaurantId) {
        return null;
    }

    @Override
    public RestaurantDto create(RestaurantDto restaurantDto) {
        return null;
    }

    @Override
    public RestaurantDto delete(String restaurantId) {
        return null;
    }
}
