package com.foodie.food.services.impl;

import com.foodie.food.dtos.FoodCategoryDTO;
import com.foodie.food.dtos.FoodItemDTO;
import com.foodie.food.dtos.RestaurantDto;
import com.foodie.food.entities.FoodCategory;
import com.foodie.food.entities.FoodItem;
import com.foodie.food.repositories.FoodCategoryRepo;
import com.foodie.food.repositories.FoodItemRepo;
import com.foodie.food.services.FoodItemService;
import com.foodie.food.services.external.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepo itemRepository;
    private final FoodCategoryRepo categoryRepository;
    private final RestaurantService restaurantService;
    private final WebClient webClient;

    @Override
    public FoodItemDTO createFoodItem(FoodItemDTO dto) {
        FoodCategory category = categoryRepository.findById(dto.getFoodCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        FoodItem item = FoodItem.builder()
                .id(UUID.randomUUID().toString())
                .restaurantId(dto.getRestaurantId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .foodCategory(category)
                .foodType(dto.getFoodType())
                .outOfStock(dto.isOutOfStock())
                .quantity(dto.getQuantity())
                .build();

        return mapToDTO(itemRepository.save(item));
    }

    @Override
    public FoodItemDTO getFoodItemById(String id) {
        FoodItem item=itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

//        RestaurantDto restaurantDto=restaurantService.getById(item.getRestaurantId());

        RestaurantDto restaurantDto= webClient.get()
                .uri("/api/v1/restaurants/{id}",item.getRestaurantId())
                .retrieve()
                .bodyToMono(RestaurantDto.class)
                .block();

        FoodItemDTO dto=mapToDTO(item);
        dto.setRestaurant(restaurantDto);
        return dto;

    }

    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodItemDTO> getByRestaurant(String restaurantId) {
        return itemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FoodItemDTO updateFoodItem(String id, FoodItemDTO dto) {
        FoodItem item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setQuantity(dto.getQuantity());
        item.setOutOfStock(dto.isOutOfStock());
        item.setFoodType(dto.getFoodType());

        return mapToDTO(itemRepository.save(item));
    }

    @Override
    public void deleteFoodItem(String id) {
        itemRepository.deleteById(id);
    }

    private FoodItemDTO mapToDTO(FoodItem item) {

        FoodCategoryDTO foodCategory=FoodCategoryDTO.builder()
                .id(item.getFoodCategory().getId())
                .name(item.getFoodCategory().getName())
                .description(item.getFoodCategory().getDescription())
                .build();

        return FoodItemDTO.builder()
                .id(item.getId())
                .restaurantId(item.getRestaurantId())
                .title(item.getTitle())
                .description(item.getDescription())
                .quantity(item.getQuantity())
                .outOfStock(item.isOutOfStock())
                .foodCategory(foodCategory)
                .foodType(item.getFoodType())
                .foodCategoryId(item.getFoodCategory().getId())
                .build();

    }
}
