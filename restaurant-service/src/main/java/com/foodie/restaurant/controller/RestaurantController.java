package com.foodie.restaurant.controller;

import com.foodie.restaurant.dto.RestaurantDto;
import com.foodie.restaurant.services.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto dto) {
        RestaurantDto saved = restaurantService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(
            @PathVariable String id,
            @RequestBody RestaurantDto dto
    ) {
        RestaurantDto updated = restaurantService.update(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable String id) {
        System.out.println("retried");
        RestaurantDto dto = restaurantService.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Search restaurants by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<RestaurantDto>> searchByName(@PathVariable String name) {
        List<RestaurantDto> results = restaurantService.findByName(name);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    // Get all restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> list = restaurantService.getAll();
        return ResponseEntity.ok(list);
    }

    // Delete restaurant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable String id) {
        restaurantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
