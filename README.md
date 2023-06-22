# Java_SpringBoot_E-Commerce_Application

Languages and Frameworks: Java, Maven, Spring Boot Web, Spring Boot Cloud, Spring Security, MySQL, MongoDB, Postgres DB, Resilience 4J, Apache Kafka, API Gateway, Keycloak, Netflix Eureka, Zipkin, Docker, Prometheus, and Grafna.

**Features**

The project encompasses the following key features:

### **1. Service Discovery**

Service discovery is implemented using Netflix Eureka. It enables automatic registration and discovery of microservices in the system, allowing them to locate and communicate with each other without explicit configuration.

### **2. Centralized Configuration**

Centralized configuration management is achieved using Spring Cloud Config. It provides a central repository to store and manage configuration files for all microservices, ensuring consistency and facilitating easy configuration changes.

### **3. Distributed Tracing**

Distributed tracing is implemented using Zipkin. It enables end-to-end tracing of requests as they flow through different microservices, providing insights into the performance and behavior of the system. This allows for efficient debugging and troubleshooting of issues.

### **4. Circuit Breaker**

Resilience4J is used to implement circuit breakers in the system. Circuit breakers help prevent cascading failures by monitoring the health of services and temporarily halting requests to a failing service. This improves overall system resilience and prevents overload.

### **5. Event Driven Architecture**

The project follows an event-driven architecture paradigm using Apache Kafka as the message broker. This allows for loose coupling between microservices and enables asynchronous communication, leading to improved scalability and responsiveness.

### **6. Secure Microservices using Keycloak**

Microservices security is achieved using Keycloak, an open-source identity and access management solution. Keycloak provides authentication and authorization services, allowing for secure access control and user management across the system.

### **7. Containerised Application using Docker**

Docker is used to build images for the microservices and utilised multistage Docker buld with GCP Jib to automatically build images and push them to Docker Hub. 

### **7. Service Monitoring and Visualisation**

Microservice monitoring and visualisation is performed using Prometheus, and Grafna. Metrics related to CPU, load, Heap Space, Garbage Collection etc are monitored and visualised in a dashboard. 
