package com.foodie.food.services.impl;

import com.foodie.food.dtos.FoodCategoryDTO;
import com.foodie.food.dtos.FoodItemDTO;
import com.foodie.food.entities.FoodCategory;
import com.foodie.food.repositories.FoodCategoryRepo;
import com.foodie.food.services.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepo repository;

    @Override
    public FoodCategoryDTO createFoodCategory(FoodCategoryDTO dto) {
        FoodCategory category = FoodCategory.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        return mapToDTO(repository.save(category));
    }

    @Override
    public FoodCategoryDTO getFoodCategoryById(String id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<FoodCategoryDTO> getAllFoodCategory() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FoodCategoryDTO updateFoodCategory(String id, FoodCategoryDTO dto) {
        FoodCategory category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return mapToDTO(repository.save(category));
    }

    @Override
    public void deleteFoodCategory(String id) {
        repository.deleteById(id);
    }

    private FoodCategoryDTO mapToDTO(FoodCategory category) {
        FoodCategoryDTO dto = FoodCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();

        if (category.getFoodItems() != null) {
            dto.setFoodItems(
                    category.getFoodItems().stream().map(item -> {
                        return FoodItemDTO.builder()
                                .id(item.getId())
                                .title(item.getTitle())
                                .quantity(item.getQuantity())
                                .foodType(item.getFoodType())
                                .build();
                    }).collect(Collectors.toList())
            );
        }
        return dto;
    }
}
