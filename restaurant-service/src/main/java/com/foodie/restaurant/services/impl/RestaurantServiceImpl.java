package com.foodie.restaurant.services.impl;

import com.foodie.restaurant.dto.RestaurantDto;
import com.foodie.restaurant.entities.Restaurant;
import com.foodie.restaurant.repository.RestaurantRepo;
import com.foodie.restaurant.services.RestaurantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo repository;

    public RestaurantServiceImpl(RestaurantRepo repository) {
        this.repository = repository;
    }

    private RestaurantDto convertToDto(Restaurant restaurant) {
        RestaurantDto dto = new RestaurantDto();
        BeanUtils.copyProperties(restaurant, dto);
        return dto;
    }

    private Restaurant convertToEntity(RestaurantDto dto) {
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(dto, restaurant);
        return restaurant;
    }

    @Override
    public RestaurantDto save(RestaurantDto restaurantDto) {
        Restaurant restaurant = convertToEntity(restaurantDto);
        if (restaurant.getId() == null || restaurant.getId().isEmpty()) {
            restaurant.setId(java.util.UUID.randomUUID().toString());
        }
        Restaurant saved = repository.save(restaurant);
        return convertToDto(saved);
    }

    @Override
    public RestaurantDto update(String id, RestaurantDto restaurantDto) {
        Optional<Restaurant> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Restaurant not found with id: " + id);
        }
        Restaurant existing = optional.get();

        restaurantDto.setId(existing.getId());
        BeanUtils.copyProperties(restaurantDto, existing);
        Restaurant updated = repository.save(existing);
        return convertToDto(updated);
    }

    @Override
    public RestaurantDto getById(String id) {
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        return convertToDto(restaurant);
    }

    @Transactional
    @Override
    public List<RestaurantDto> findByName(String name) {
        Optional<Restaurant> restaurants = repository.findByNameContainingIgnoreCase(name);
        return restaurants.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Restaurant not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<RestaurantDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
