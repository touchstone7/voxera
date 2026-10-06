# Voxera

Voxera is an AI-assisted IT helpdesk and ticket management system.

The backend is being built with Java and Spring Boot, with the longer-term goal of integrating a voice agent that can interact with the ticketing system through well-defined backend APIs.

## Current Stack

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Data JPA / Hibernate
- H2 for development/testing
- PostgreSQL for production
- Flyway for database migrations
- Spring Security
- JWT authentication
- BCrypt password hashing

## Current Backend Progress

The core ticketing backend is implemented:

- Project configuration and Maven setup
- Database schema and Flyway migrations
- User and ticket domain models
- Repositories and service layer
- REST APIs for tickets
- Global exception handling
- Domain and service-layer tests
- Integration tests
- User authentication
- BCrypt password verification
- JWT generation and validation
- Stateless Spring Security
- JWT authentication filter
- Authenticated access to protected ticket APIs

## Current API Areas

### Authentication

`POST /api/v1/auth/login`

Authenticates a user and returns an access token.

### Tickets

`/api/v1/tickets`

Supports the current ticket operations including:

- Create ticket
- Retrieve ticket
- Retrieve tickets for a user
- Assign ticket
- Start ticket
- Move ticket to pending
- Resolve ticket
- Close ticket

Protected ticket endpoints require a valid JWT.

## Development Status

The backend is currently completing its authentication and authorization layer.

Next major areas:

1. Role-based authorization
2. Ticket ownership/access rules
3. Authorization integration tests
4. Ticket lifecycle and domain rules
5. Assignment and agent workflow
6. Comments and conversations
7. Voice-agent abstraction and integration

The frontend and voice-agent components will be developed after the backend foundations are sufficiently mature.