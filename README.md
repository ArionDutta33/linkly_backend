Sure Arion! Here's the complete backend `README` content in plain text:

* * *

# Linkly Backend

This is the backend service for **Linkly.io**, a modern link management platform. It provides RESTful APIs for authentication, link storage, and user-specific access control using Spring Boot, Spring Security with JWT, Spring Data JPA, and PostgreSQL.

* * *

## 🔧 Tech Stack

*   **Spring Boot** – Application framework
    
*   **Spring Security + JWT** – User authentication and authorization
    
*   **Spring Data JPA** – ORM for database access
    
*   **PostgreSQL** – Relational database
    
*   **Maven** – Dependency and build management
    

* * *

## 📂 Project Structure

    src/main/java/com/arion/linkly
    ├── config/              # JWT config, security filters
    ├── controller/          # API controllers (Auth, Link)
    ├── dto/                 # Request/response data transfer objects
    ├── entity/              # JPA entities (User, Link)
    ├── repository/          # Spring Data JPA repositories
    ├── service/             # Business logic for user and link handling
    └── LinklyApplication.java
    

* * *

## ⚙️ Environment Configuration

Create a file named `application.properties` in `src/main/resources/`:

    # Server
    server.port=8080
    
    # PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/linkly
    spring.datasource.username=your_pg_user
    spring.datasource.password=your_pg_password
    spring.jpa.hibernate.ddl-auto=update
    
    # JWT
    jwt.secret=your_super_secret_key
    jwt.expiration=86400000
    

You may also use `application.yml` if you prefer YAML format.

* * *

## 🚀 Running the App

Make sure PostgreSQL is running and a `linkly` database is created:

    createdb linkly
    

Then build and run the app:

    ./mvnw spring-boot:run
    

The app will start at:  
**[http://localhost:8080](http://localhost:8080)**

* * *

## 📫 API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | /api/auth/register | Register a new user |
| POST | /api/auth/login | Authenticate and get JWT |
| GET | /api/links | Get all user links |
| POST | /api/links | Save a new link |
| PUT | /api/links/{id} | Update a link |
| DELETE | /api/links/{id} | Delete a link |

All `/api/links` routes require a valid JWT in the `Authorization` header:

    Authorization: Bearer <your_token>
    

* * *

## 🔐 Authentication Flow

*   Users register and login via `/auth/*` endpoints.
    
*   On login, a JWT is issued.
    
*   Protected endpoints verify the JWT using a custom Spring Security filter.
    

* * *

## 🧪 Testing

You can use Postman or curl to test endpoints. Make sure to set the `Authorization` header after login:

    Authorization: Bearer <token>
    

* * *

## 📌 Notes

*   Passwords are hashed using BCrypt.
    
*   JWT expiration and secret are configurable.
    
*   CORS is enabled for the frontend to communicate during development.
    

* * *

## 🧑‍💻 Author

**Arion Dutta**  
Kaziranga University  
[ariondutta333@outlook.com](mailto:ariondutta333@outlook.com)

* * *

## 📄 License

MIT License – feel free to use, modify, and distribute with credit.

* * *

Let me know if you want a `.md` file version or want this added to your actual project directory structure.
