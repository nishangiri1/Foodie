package com.foodie.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("food-service",route->{
                    return route.path("/foods/**")
                            .uri("lb://food-service");
                })
                .route("restaurant-service",route->{
                    return route.path("/restaurants/**")
                            .uri("lb://restaurant-service");
                })
                .build();
    }
}
