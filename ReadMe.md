E-Commerce Backend

A RESTful backend API for an e-commerce application, built with Java and Spring Boot. It handles products, orders, and order items, and exposes clean DTO-based responses to clients.

Features
Product management (create, read, update, delete)
Order placement and order history
Order items with price and subtotal calculation (BigDecimal for money)
DTO layer for clean API responses (OrderResponse, OrderItemDTO)
Layered architecture: Controller, Service, Repository
<Add: JWT authentication / user management / cart, if you have them>
Tech Stack
Area	Technology
Language	Java <17>
Framework	Spring Boot
Data access	Spring Data JPA / Hibernate
Database	<MySQL / PostgreSQL / H2>
Build tool	<Maven / Gradle>
Utilities	Lombok
Project Structure
src/main/java/com/backendapi/api
├── controller      # REST controllers
├── service         # Business logic
├── repository      # JPA repositories
├── model           # Entities (OrderModel, OrderItem, Product, ...)
└── dtos
    └── respdto     # Response DTOs (OrderResponse, OrderItemDTO, ...)
Getting Started
Prerequisites
JDK <17> or higher
<Maven / Gradle>
<MySQL / PostgreSQL> running locally (skip if using H2)
Installation
bash
# 1. Clone the repository
git clone https://github.com/<your-username>/<repo-name>.git

# 2. Go to the project folder
cd <repo-name>
Configure the database

Edit src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/<db_name>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Never commit real passwords. Use environment variables or a separate local config file.

Run the application
bash
# Maven
./mvnw spring-boot:run

# Gradle
./gradlew bootRun

The server starts at http://localhost:8080.

API Endpoints

Update this table to match your controllers.

Method	Endpoint	Description
GET	/api/products	Get all products
GET	/api/products/{id}	Get product by ID
POST	/api/products	Create a product
POST	/api/orders	Place a new order
GET	/api/orders	Get all orders
GET	/api/orders/{id}	Get order by ID
Sample response: GET /api/orders/1
json
{
  "id": 1,
  "totalAmount": 1998.00,
  "status": "PENDING",
  "items": [
    {
      "id": 1,
      "productId": 10,
      "quantity": 2,
      "price": 999.00,
      "subTotal": 1998.00
    }
  ],
  "createdAt": "2026-10-05T10:30:00"
}
Running Tests
bash
./mvnw test
Future Improvements
Authentication and authorization (Spring Security + JWT)
Payment integration
Pagination and filtering for products and orders
Docker support
Deployment on AWS
Author

Rahmat Ali GitHub: @Rahmat907