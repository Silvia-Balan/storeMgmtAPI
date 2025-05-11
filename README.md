# Store Management API

A backend-only application built using **Spring Boot** and **Spring MVC**, designed to serve as a simple store management system, with features such as adding, updating, retrieving products, role-based access control, error handling and logging.

## Features

- Product management: add, retrieve, update, delete products
- Spring basic authentication (H2 database)
- Role-based access control (`CLIENT`, `VENDOR`, `ADMIN`)
- Validation using Jakarta Bean Validation
- Global error handling and custom exceptions
- Logging of actions and errors using SLF4J
- Java 17+ features
- Unit tests
- H2 in-memory database for development/testing

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- H2 Database
- Maven (project management)
- MapStruct (DTO mapping)
- JUnit 5 (for testing)
- Postman (for testing)

##Endpoints Overview

| Method | Endpoint                   | Roles Allowed     | Description              |
|--------|-------------------------   |-------------------|--------------------------|
| GET    | `/products`                | CLIENT            | View all products        |
| GET    | `/products/product/**`     | CLIENT            | View specific product    |
| GET    | `/products/product?name=**`| CLIENT            | View products containing a specific string in name |
| POST   | `/products`                | VENDOR            | Add a new product        |
| PUT    | `/products/product/{name}` | VENDOR            | Update a product         |
| DELETE | `/products/{name}`         | ADMIN             | Delete a product         |
| DELETE | `/products`                | ADMIN             | Delete all products      |

## Getting Started

### Pre-requisites

- Java 21 installed
- IntelliJ or other IDE installed
- Maven installed
- Postman or similar tool installed (for testing)

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/Silvia-Balan/storeMgmtAPI.git
   cd storeMgmtAPI

2. **Build the project**
   ```bash
   mvn clean install

3. **Run the application**
   mvn spring-boot:run

4. **Access H2 Console**
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- User: sa
- Password: (empty)

### API Authentication
Uses Spring Basic authentication (tables users and authorities) with bcrypt encryption for passwords.
Test credentials can be found in the schema.sql file as below. 

| Username | Password    | Roles                                |
| -------- | ----------  | -----------------------------------  |
| admin    | admin1Pswd  | ROLE_ADMIN, ROLE_VENDOR, ROLE_CLIENT |
| vendor   | vendor1Pswd | ROLE_VENDOR, ROLE_CLIENT 		|
| client   | client1Pswd | ROLE_CLIENT 				|

#### Unit Testing
- Tests are placed under src\test\java\com\store\mgmtAPI

#### Future Improvements
- Add integration tests
- Extend product features (inventory, categories, order management)
- Improve product search by allowing users to search for products by category or price range
- Add possibility of applying discounts on products
- Add product reviews and ratings
