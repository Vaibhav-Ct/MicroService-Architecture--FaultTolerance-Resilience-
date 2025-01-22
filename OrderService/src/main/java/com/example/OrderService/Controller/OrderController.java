package com.example.OrderService.Controller;

import com.example.OrderService.Client.UserServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final UserServiceClient userServiceClient;

    public OrderController(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @GetMapping("/user")
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackMethod")
    public String getUserInfo(@RequestParam String id) {
        try {
            return userServiceClient.getUserById(id) + " Order Received";
        } catch (InterruptedException e) {
            // Log or handle the InterruptedException
            Thread.currentThread().interrupt(); // Restore the interrupted status
            return "An error occurred while processing the request: " + e.getMessage();
        }
    }

    // Fallback method to handle failures
    public String fallbackMethod(String id, Throwable throwable) {
        return "Fallback response: Unable to retrieve user information for ID " + id +
                ". Reason: " + throwable.getMessage();
    }
}
