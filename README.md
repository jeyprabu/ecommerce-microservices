# Ecommerce Microservices Architecture

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- RBAC
- Apache Kafka
- MongoDB
- MySQL
- Docker Compose
- API Gateway
- Maven Multi Module

## Microservices

- auth-service
- customer-service
- order-service
- inventory-service
- api-gateway
- common-library

## Features

- JWT Authentication
- Refresh Tokens
- Role Based Access Control
- Kafka Event Driven Communication
- MongoDB + MySQL Polyglot Persistence
- Shared Common Library
- Dockerized Development Environment

## Run

```bash
mvn clean install -DskipTests
docker compose up
