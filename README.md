# User and Order Service Microservices

This project demonstrates the implementation of Microservice Architecture with Fault Tolerance using Resilience4j. The architecture includes **User Service** and **Order Service**, communicating via Feign Client with built-in Circuit Breaker support.

## 📋 Project Structure

- **User Service**: Runs on port 8080.
- **Order Service**: Runs on port 8081.

## 📂 Directory Structure

- `/OrderService`  
  - `src/main/java/com/example/OrderService`  
    - `Controller/OrderController.java`  
    - `Client/UserServiceClient.java`  
    - `OrderServiceApplication.java`  

- `/UserService`  
  - `src/main/java/com/example/UserService`  
    - `Controller/UserController.java`  
    - `UserServiceApplication.java`  

---

## 🛠 **User Service**

The User Service handles basic user retrieval requests.

### **Endpoint**: `/api/id`

#### Functionality:
- Retrieves user information based on the provided `id`.

---

## 🛠 **Order Service**

The Order Service retrieves user information and manages orders. It interacts with the User Service using Feign Client and Circuit Breaker for fault tolerance.

---

## 🛠 **Feign Client**

The Feign Client acts as a bridge to call the User Service.

---

## 🛠 **Circuit Breaker Configuration**

Fault tolerance is achieved using Resilience4j. The circuit breaker manages retries, fallback methods, and transitions between Open and Half-Open states.

---

## 🚨 **Fault Tolerance in Action**

### Normal State:
- Requests are handled normally.

### Simulated Delay:
- The User Service response takes more than 3 seconds.

### Circuit Breaker Opens:
- After 3 slow responses.

### Open State:
- Circuit breaker enters Open state and returns fallback responses immediately.

### Fallback:
- `🚨 Fallback response: Unable to retrieve user information for ID 123. Reason: java.lang.InterruptedException`

### Half-Open State:
- After 5 seconds, circuit breaker allows 2 test calls.
  - **Successful Test**: Circuit transitions to Closed.
  - **Failed Test**: Circuit reverts to Open.

### Recovery:
- Once the issue is resolved, the system transitions back to Closed and handles requests normally again.

---

## 🔧 **Running the Services**

- **Start User Service**: Run `UserServiceApplication` on port 8080.
- **Start Order Service**: Run `OrderServiceApplication` on port 8081.

---

## 🚀 **Testing with Postman**

### Normal Request:
- Send a GET request to `http://localhost:8081/order/user?id=123`.
- **Expected Response**: `User ID: 123 Order Received`

### Delayed Request:
- Send the same GET request again to simulate a delay.
- **Fallback Response**: `🚨 Fallback response: Unable to retrieve user information for ID 123. Reason: java.lang.InterruptedException`

### Open State:
- Further requests trigger fallback until the circuit closes again.

### Half-Open State:
- After 5 seconds, send test requests to verify functionality.

---

## 📝 **Conclusion**

This project showcases a Microservices Architecture with Fault Tolerance using Resilience4j. It handles errors gracefully by using Circuit Breakers and Fallback Methods, ensuring reliability in distributed systems.
