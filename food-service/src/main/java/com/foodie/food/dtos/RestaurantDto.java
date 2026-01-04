package com.foodie.food.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private String id;

    private String name;

    private String address;

    private String phone;


    private ArrayList<String> picture=new ArrayList<>();

    private boolean open=false;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private String aboutRestaurant;
}
