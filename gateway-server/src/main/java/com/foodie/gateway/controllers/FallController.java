package com.foodie.gateway.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallController {
    @RequestMapping("/circuitBreaker/fallback")
    public Mono<String> circuitBreakerFoodFallBack() {
        return Mono.just("Food service is down. Contact to service provider");
    }
}
