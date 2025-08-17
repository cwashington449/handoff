# Handoff Backend

Spring Boot 3.5.x backend for the Handoff platform with JPA, JWT-based Security, and CORS.

## Tech
- Spring Boot 3.5.x (Web, Data JPA, Security, Validation, Actuator)
- JWT (jjwt)
- PostgreSQL + Flyway (all profiles)
- Springdoc OpenAPI (Swagger UI)

## Quick Start

Requirements:
- Java 21
- Maven 3.9+
- Docker Desktop (for local Postgres + app)

Build the JAR:

```bash
mvn -DskipTests package
```

Run locally with Docker Compose (Postgres 16.10 + app):

```bash
# From repo root
docker compose up -d --build
# Check health
curl http://localhost:8080/api/v1/actuator/health
```

- API base: http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/api/v1/swagger-ui/index.html

The app runs with Spring profile `local` and connects to the `db` service using env vars (DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD) defined in docker-compose.yml. Flyway is enabled; JPA `ddl-auto=validate`.

## Configuration
See `src/main/resources/application.yml` for `local`, `nonprod`, and `prod` profiles.

Key properties:
- `jwt.secret`, `jwt.expiration`
- `cors.allowed-origins`
- `spring.datasource.*`

## Testing
Integration tests use Testcontainers (PostgreSQL 16.10) under the `test` profile.

```bash
mvn test
```

## Notes
- Local Testcontainers auto-database has been disabled; use docker-compose for local dev.
- Flyway manages schema across all profiles; ensure migrations are up to date.
