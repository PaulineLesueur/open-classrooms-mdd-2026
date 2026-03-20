# Monde de Dev

![Angular](https://img.shields.io/badge/Angular-v14-red?logo=angular&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-blue?logo=typescript&logoColor=white)
![RxJS](https://img.shields.io/badge/RxJS-v7.5-B7178C?logo=reactivex&logoColor=white)
![PrimeNG](https://img.shields.io/badge/PrimeNG-v14-3B82F6?logo=primeng&logoColor=white)
![Node.js](https://img.shields.io/badge/node.js-v22.21.1-43853D?logo=node.js&logoColor=white)
![npm](https://img.shields.io/badge/npm-v10.9.4-CB3837?logo=npm&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v2.7.3-6DB33F?logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-11-007396?logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens&logoColor=white)

**Monde de Dev** is a full-stack developer community platform built as part of a training project.
It allows developers to browse posts by topic, subscribe to topics of interest, write comments, and manage their profile.

The project includes:
- A **feed** page listing all posts, sortable by date, filtered by the user's subscribed topics.
- A **post detail** page displaying the full content of a post and its comments.
- A **post creation** form allowing authenticated users to publish a new post linked to a topic.
- A **topics** page listing all available topics with the option to subscribe or unsubscribe.
- A **profile** page for managing username, email, password and viewing current subscriptions.
- A **JWT-based authentication** system (register / login) with stateless token management.

<br>

## Technical Highlights

| Area | Description |
|------|-------------|
| **Frontend Framework** | Angular 14 |
| **UI Library** | PrimeNG 14 + PrimeFlex |
| **Language** | TypeScript |
| **Reactive Programming** | RxJS Observables with `takeUntil()` pattern for memory management |
| **HTTP** | Angular HttpClient with a JWT interceptor |
| **Backend Framework** | Spring Boot 2.7.3 |
| **Language** | Java 11 |
| **Security** | Spring Security 5 with JWT (HMAC-SHA256), stateless session |
| **Persistence** | Spring Data JPA + Hibernate, MySQL database |
| **Build Tool** | Maven 3.9.6 |
| **Documentation** | Javadoc (backend) · Compodoc (frontend) |

<br>

## Prerequisites

- **Java** 11
- **Maven** 3.9+
- **MySQL** 8+
- **Node.js** v22.21.1
- **npm** v10.9.4
- **Angular CLI** v14

<br>

## Installation & Setup

### 1. Clone the repository
```bash
git clone <repository-url>
cd MondedeDev
```

### 2. Database
Create the MySQL database (or let Hibernate create it automatically on first run via `createDatabaseIfNotExist=true`):
```sql
CREATE DATABASE mdd;
```

Configure your credentials in `back/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mdd?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Backend
```bash
cd back
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.
On first startup, the seeders automatically populate the database with topics, a test user, posts and comments.

**Test account:**
| Field | Value |
|-------|-------|
| Email | `test@test.com` |
| Password | `Test1234!` |

### 4. Frontend
```bash
cd front
npm install
ng serve
```

Visit `http://localhost:4200/` in your browser.

<br>

## API Endpoints

| Method | Route | Description | Auth required |
|--------|-------|-------------|:---:|
| `POST` | `/api/auth/register` | Register a new user | ✗ |
| `POST` | `/api/auth/login` | Login and receive a JWT | ✗ |
| `GET` | `/api/users/{id}` | Get user profile | ✓ |
| `PUT` | `/api/users/{id}` | Update username / email | ✓ |
| `PUT` | `/api/users/{id}/password` | Update password | ✓ |
| `GET` | `/api/posts` | List all posts | ✓ |
| `GET` | `/api/posts/{id}` | Get a single post | ✓ |
| `POST` | `/api/posts` | Create a new post | ✓ |
| `GET` | `/api/post/{postId}/comments` | List comments for a post | ✓ |
| `POST` | `/api/comments` | Add a comment to a post | ✓ |
| `GET` | `/api/topics` | List all topics | ✓ |
| `GET` | `/api/topics/{id}` | Get a single topic | ✓ |
| `GET` | `/api/subscriptions` | Get current user's subscriptions | ✓ |
| `POST` | `/api/subscriptions/{topicId}` | Subscribe to a topic | ✓ |
| `DELETE` | `/api/subscriptions/{topicId}` | Unsubscribe from a topic | ✓ |

<br>

## Project Structure

### Backend (`back/`)
- **`entities/`** — JPA entities: `User`, `Post`, `Topic`, `Comment`
- **`dto/requests/`** — Incoming request payloads: `RegisterRequest`, `LoginRequest`, `PostRequest`, `CommentRequest`, `UserRequest`, `UserPasswordRequest`
- **`dto/responses/`** — Outgoing response payloads: `AuthResponse`, `PostResponse`, `TopicResponse`, `CommentResponse`, `UserResponse`
- **`repositories/`** — Spring Data JPA interfaces for each entity
- **`mappers/`** — Manual entity ↔ DTO converters
- **`services/`** — Business logic: `UserService`, `PostService`, `TopicService`, `CommentService`
- **`controllers/`** — REST controllers: `AuthController`, `UserController`, `PostController`, `TopicController`, `CommentController`, `SubscriptionController`
- **`security/`** — JWT filter, token provider, `UserDetailsServiceImpl`, `SecurityConfig`, `AuthUtils`
- **`Seeders/`** — `CommandLineRunner` seeders ordered by dependency: `TopicSeeder` → `UserSeeder` → `PostSeeder` → `CommentSeeder`

### Frontend (`front/src/app/`)
- **`core/models/`** — TypeScript interfaces: `PostResponse`, `PostRequest`, `TopicResponse`, `CommentResponse`, `CommentRequest`, `UserResponse`, `AuthResponse`
- **`core/services/`** — HTTP services: `PostService`, `TopicService`, `CommentService`, `SubscriptionService`, `UserService`, `AuthService`
- **`core/guards/`** — `AuthGuard` to protect authenticated routes
- **`core/interceptors/`** — `AuthInterceptor` automatically attaches the JWT to every outgoing request
- **`features/posts/`**
  - **`feed/`** — Post feed with date sorting. Route `/feed`
  - **`post-detail/`** — Full post view with comments. Route `/post/detail/:id`
  - **`create-form/`** — Post creation form. Route `/post/create`
- **`features/topics/`**
  - **`topics-list/`** — Topics directory with subscribe / unsubscribe. Route `/topics`
- **`features/auth/`** — `register/` and `login/` forms. Routes `/auth/register`, `/auth/login`
- **`features/profile/`** — Profile management (info + subscriptions). Route `/profile`
- **`shared/components/navbar/`** — Navigation bar with routing links

<br>

## Documentation

### Frontend (Compodoc)
```bash
cd front
npm run compodoc
```
Serves the Angular documentation at `http://localhost:8084/`.

### Backend (Javadoc)
```bash
cd back
mvn javadoc:javadoc
```
The generated documentation is available in `back/target/site/apidocs/index.html`.

### Backend (Swagger UI)
With the backend running, the interactive API documentation is available at:

`http://localhost:8080/swagger-ui.html`

It is powered by **springdoc-openapi** and lists all endpoints with their request/response schemas. To test protected routes directly from the UI, click **Authorize** and enter your JWT token as `Bearer <token>`.

<br>

## Notes
This project is a **fictional educational project** developed as part of an **OpenClassrooms certification program**. It was created for learning purposes only and does not represent a real-world application or organization.
