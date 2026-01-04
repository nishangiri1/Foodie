    package com.foodie.food.entities;

    import jakarta.persistence.*;
    import lombok.*;

    @Builder
    @Entity
    @Table(name = "food_service_food_item")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class FoodItem {
        @Id
        private String id;

        @Column(nullable = false)
        private String restaurantId;

        private String title;

        private String description;

        private int quantity;

        private boolean outOfStock=true;

        @Enumerated(EnumType.STRING)
        private FoodType foodType=FoodType.VEG;

        @ManyToOne
        private FoodCategory foodCategory;
    }
