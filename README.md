# Event Ledger System

## Overview

Microservice application to process account transactions using an event-driven approach.

The system contains two Spring Boot microservices:

- Event Gateway Service
- Account Service

The Gateway receives transaction events and communicates with the Account Service to maintain account balances.

## Services

### Event Gateway Service

- Accepts transaction events
- Request validation
- Idempotency handling using eventId
- Communicates with Account Service using OpenFeign
- Resilience4j circuit breaker for Account Service failures
- Trace ID propagation and logging
- Global exception handling

### Account Service

- Maintains account balances
- Processes deposit and withdrawal transactions
- Prevents duplicate transactions
- Calculates balance based on transaction history
- Account validation
- Global exception handling

## Technology Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- OpenFeign
- Resilience4j
- Spring Actuator
- JUnit 5
- Mockito

## Ports

- Event Gateway Service: 8080
- Account Service: 8081

## APIs

### Event Gateway Service

POST `/events`

GET `/events/{id}`

### Account Service

POST `/accounts/{id}/transactions`

GET `/accounts/{id}/balance`

## Testing

Automated tests cover:

- Transaction validation
- Idempotency handling
- Balance calculation
- Duplicate transaction handling
- Resiliency behavior
- Service layer testing
- Gateway to Account Service flow testing
- Exception handling scenarios

Run tests:

Windows:

./mvnw.cmd clean test