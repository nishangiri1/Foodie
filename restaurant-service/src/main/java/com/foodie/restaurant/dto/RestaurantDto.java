package com.foodie.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class RestaurantDto {
    private String id;

    private String name;

    private String address;

    private String phone;


    private List<String> picture=new ArrayList<>();

    private boolean open=false;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private String aboutRestaurant;
}
