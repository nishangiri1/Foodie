package com.foodie.restaurant.services;

import com.foodie.restaurant.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto save(RestaurantDto restaurantDto);

    RestaurantDto update(String id, RestaurantDto restaurantDto);

    RestaurantDto getById(String id);

    List<RestaurantDto> findByName(String name);

    void delete(String id);

    List<RestaurantDto> getAll();
}

