# Financial Information

## Build and Run
Execute the following commands in the current directory `/financial-information-spring-boot`.

Build project:

    ./gradlew build

Run all tests:

    ./gradlew test

Start database:

    docker compose -f ./src/main/docker/docker-compose.yml up

Connect to database:

    jdbc:postgresql://localhost:5432/postgres

## Goal
Get financial information (e.g., gold price) from a web API. Persistate them in a relational database and visualize the results.

## Tech Stack
- Java 17
- Gradle
- Spring Boot
- Spring Web
- Spring Security
- Spring Data
- REST
- SQL
- H2 in-memory database
- Docker
- Postgres

## Principles
- Test Driven Development (TDD)
- Model View Controller (MVC)
- Red, Green, Refactor loop

## Architecture
Controller Repository Architecture to address separation of concerns