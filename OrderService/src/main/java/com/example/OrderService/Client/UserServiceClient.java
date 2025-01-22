package com.example.OrderService.Client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8080/api")
public interface UserServiceClient {

    @GetMapping("/id")
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackMethod")
    default String getUserById(@RequestParam String id) throws InterruptedException {
        // Simulate a 3-second delay
        Thread.sleep(3000);
        return "User ID: " + id;
    }

    // Fallback method
    default String fallbackMethod(String id, Throwable throwable) {
        return "Fallback response: Unable to retrieve user information for ID " + id +
                ". Reason: " + throwable.getMessage();
    }
}
