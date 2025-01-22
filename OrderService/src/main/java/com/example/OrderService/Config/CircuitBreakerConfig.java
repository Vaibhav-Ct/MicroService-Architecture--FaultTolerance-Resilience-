package com.example.OrderService.Config;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfig {

    @Bean
    public io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry circuitBreakerRegistry() {
        return io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry.ofDefaults();
    }

}
