# User Registration Microservice

## Project Overview
The User Registration Microservice is a REST API application developed using Spring Boot. It allows users to register, log in, retrieve user details, update user information, and delete user records. It uses PostgreSQL for data storage and JWT for authentication.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Spring Security
- JWT (JSON Web Token)
- Netflix Eureka Client
- Swagger UI / OpenAPI
- Maven

## Features
- User registration
- User login with JWT token generation
- Get all users
- Get user details by ID
- Search user by email
- Update user information
- Delete user records
- PostgreSQL database integration
- API documentation using Swagger UI
- Service registration with Eureka

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/users/register` | Register a new user |
| POST | `/users/login` | Authenticate a user |
| GET | `/users` | Retrieve all users |
| GET | `/users/{id}` | Retrieve a user by ID |
| GET | `/users/email?email={email}` | Retrieve a user by email |
| PUT | `/users/{id}` | Update user information |
| DELETE | `/users/{id}` | Delete a user |

## Configuration

Configure your PostgreSQL database and environment variable before running the application.

```properties
spring.application.name=User
server.port=8093
spring.datasource.url=jdbc:postgresql://localhost:5432/user
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
```

Set the `DB_PASSWORD` environment variable to your PostgreSQL password. Do not upload passwords or secret keys to GitHub.

## How to Run

1. Install Java 17 and PostgreSQL.
2. Create a PostgreSQL database named `user`.
3. Configure the database connection and `DB_PASSWORD` environment variable.
4. Start the Eureka server on port `8761`, if service discovery is required.
5. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The application runs at:

`http://localhost:8093`

## API Documentation

Open Swagger UI in your browser:

`http://localhost:8093/swagger-ui/index.html`

## Future Improvements
- Add role-based authorization.
- Hide sensitive user data from API responses.
- Add input validation and exception handling.
- Use DTOs for request and response objects.
- Add automated unit and integration tests.

## Author
**Srilakshmi**

## License
This project is intended for learning and educational purposes.
