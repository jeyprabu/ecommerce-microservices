# Ecommerce Microservices Backend

Event-driven ecommerce backend built using:

- Java 17
- Spring Boot
- Spring Cloud Gateway
- Apache Kafka
- MongoDB
- MySQL
- Docker Compose
- Multi-module Maven

## Architecture

- customer-service
- order-service
- inventory-service
- api-gateway
- common-library

## Features

- REST APIs
- Kafka producer/consumer
- Event-driven architecture
- API Gateway routing
- Polyglot persistence
- Dockerized infrastructure

## Run

```bash
mvn clean install -DskipTests
docker compose up
