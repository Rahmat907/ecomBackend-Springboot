# E-Commerce Backend (Spring Boot)

E-commerce backend REST API built with Java and Spring Boot, featuring authentication and authorization, product and order management, and DTO-based responses.

The project follows a layered architecture (Controller, Service, Repository) and uses DTOs to keep API responses separate from database entities. Money values are handled with `BigDecimal` to avoid floating-point errors.

## Features

- Authentication and authorization with Spring Security
- Product management (create, read, update, delete)
- Order placement and order details with item-wise quantity, price and subtotal
- DTO-based API responses (`OrderResponse`, `OrderItemDTO`)
- Layered architecture: Controller, Service, Repository

## Tech Stack

| Area | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Security | Spring Security |
| Data access | Spring Data JPA, Hibernate |
| Database | MySQL |
| Build tool | Maven |
| Utilities | Lombok |

## Project Structure

```
src/main/java/com/backendapi/api
├── controller
├── service
├── repository
├── model
└── dtos
    └── respdto
```

## Getting Started

### Prerequisites

- JDK 17 or higher
- MySQL running locally

### Clone the repository

```bash
git clone https://github.com/Rahmat907/ecomBackend-Springboot.git
cd ecomBackend-Springboot
```

### Configure the application

Create `src/main/resources/application.properties` and add your own values:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

This file is not included in the repository because it contains private credentials.

### Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The server starts at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create a product |
| POST | `/api/orders` | Place a new order |
| GET | `/api/orders/{id}` | Get order by ID |

### Sample response: `GET /api/orders/1`

```json
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
```

## Future Improvements

- Payment integration
- Pagination and filtering
- Docker support
- Deployment on AWS

## Author

**Rahmat Ali**
GitHub: [Rahmat907](https://github.com/Rahmat907)