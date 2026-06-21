# Event Ledger System
## Overview
Microservice application to process account transactions using event-driven approach.

## Services
### Event Gateway Service
- Accepts transaction events
- Idempotency using eventId- Calls Account Service
- Resilience4j circuit breaker
- Trace ID logging
- Global exception handling

### Account Service
- Maintains account balance
- Processes deposit and withdrawal transactions
- Prevents duplicate transactions
- Global exception handling

## Technology Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- OpenFeign
- Resilience4j
- Actuator
- JUnit 5 Mockito

## Ports
Event Gateway Service: 8080
Account Service: 8081

## APIs
Event Gateway:
POST /events
GET /events/{id}

Account Service:
POST /accounts/{id}/transactions
GET /accounts/{id}/balance
## Testing
Run:
mvn test