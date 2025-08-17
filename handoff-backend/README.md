# Handoff Backend

Spring Boot 3.5.x backend for the Handoff platform with JPA, JWT-based Security, and CORS.

## Tech
- Spring Boot 3.5.x (Web, Data JPA, Security, Validation, Actuator)
- JWT (jjwt)
- PostgreSQL + Flyway (all profiles)
- Springdoc OpenAPI (Swagger UI)

## URLs
- API base: http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/api/v1/swagger-ui/index.html
- Health: http://localhost:8080/api/v1/actuator/health

## Requirements
- Java 21
- Maven 3.9+
- Docker Desktop (optional: for local Postgres + containerized app)

---

## Profiles overview
Spring profile is controlled by SPRING_PROFILES_ACTIVE (defaults to local):
- local: Dev defaults. Connects to Postgres with Flyway enabled. show-sql=true.
- nonprod: For staging/testing. Flyway enabled. show-sql=false.
- prod: For production. Flyway enabled. Requires explicit DB_URL/creds. show-sql=false.

Common environment variables (see application.yml):
- JWT_SECRET, JWT_EXPIRATION
- CORS_ALLOWED_ORIGINS
- DB_URL or DB_HOST/DB_PORT/DB_NAME/DB_USERNAME/DB_PASSWORD

---

## Quick start (Local via Docker Compose)
From the repo root:

```bash
# Build backend JAR (skips tests for speed)
mvn -DskipTests -f handoff-backend/pom.xml package

# Start Postgres + backend (profile=local)
docker compose up -d --build

# Check health
curl http://localhost:8080/api/v1/actuator/health
```

compose uses docker/backend.Dockerfile and sets:
- SPRING_PROFILES_ACTIVE=local
- DB_* vars pointing to the db service

---

## Local development (without Docker)
Option A: run with Maven
```bash
# In handoff-backend/
export SPRING_PROFILES_ACTIVE=local
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=handoff
export DB_USERNAME=handoff
export DB_PASSWORD=handoff
export JWT_SECRET=change-this-secret-please
export CORS_ALLOWED_ORIGINS=http://localhost:5173

mvn spring-boot:run
```

Option B: run a built JAR
```bash
# In handoff-backend/
mvn -DskipTests package

export SPRING_PROFILES_ACTIVE=local
# Either DB_URL or the individual parts
export DB_URL=jdbc:postgresql://localhost:5432/handoff
export DB_USERNAME=handoff
export DB_PASSWORD=handoff

java -jar target/handoff-backend-0.0.1-SNAPSHOT.jar
```

Make sure a local Postgres is running and accessible with the above credentials (docker-compose db service is fine).

---

## Build container image
Two equivalent options exist:

Option A (root Dockerfile wrapper):
```bash
# From repo root, uses docker/backend.Dockerfile
docker build -f docker/backend.Dockerfile -t handoff-backend:latest .
```

Option B (module Dockerfile):
```bash
# From handoff-backend/ module
docker build -f Dockerfile -t handoff-backend:latest .
```

---

## Run in nonprod
Nonprod expects profile nonprod with your database and JWT/CORS settings.

Run JAR:
```bash
# In handoff-backend/
export SPRING_PROFILES_ACTIVE=nonprod
export DB_URL="jdbc:postgresql://<host>:<port>/<db>"
export DB_USERNAME=<username>
export DB_PASSWORD=<password>
export JWT_SECRET=<strong-random-secret>
export CORS_ALLOWED_ORIGINS=https://<your-nonprod-frontend>

java -jar target/handoff-backend-0.0.1-SNAPSHOT.jar
```

Run container:
```bash
docker run --rm -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=nonprod \
  -e DB_URL="jdbc:postgresql://<host>:<port>/<db>" \
  -e DB_USERNAME=<username> \
  -e DB_PASSWORD=<password> \
  -e JWT_SECRET=<strong-random-secret> \
  -e CORS_ALLOWED_ORIGINS=https://<your-nonprod-frontend> \
  handoff-backend:latest
```

---

## Run in prod
Prod requires explicit DB_URL and credentials; defaults are not used. Configure secrets via your platform (Kubernetes, ECS, VM env, etc.).

Run JAR:
```bash
# In handoff-backend/
export SPRING_PROFILES_ACTIVE=prod
export DB_URL="jdbc:postgresql://<prod-host>:<port>/<db>"
export DB_USERNAME=<prod-username>
export DB_PASSWORD=<prod-password>
export JWT_SECRET=<long-strong-secret>
export CORS_ALLOWED_ORIGINS=https://<your-prod-frontend>
# Optional JVM tuning
export JAVA_OPTS="-Xms256m -Xmx512m"

java $JAVA_OPTS -jar target/handoff-backend-0.0.1-SNAPSHOT.jar
```

Run container:
```bash
docker run --rm -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL="jdbc:postgresql://<prod-host>:<port>/<db>" \
  -e DB_USERNAME=<prod-username> \
  -e DB_PASSWORD=<prod-password> \
  -e JWT_SECRET=<long-strong-secret> \
  -e CORS_ALLOWED_ORIGINS=https://<your-prod-frontend> \
  -e JAVA_OPTS="-Xms256m -Xmx512m" \
  handoff-backend:latest
```

---

## Notes
- Flyway is enabled in all profiles; ensure migrations are up to date before deploys.
- The server runs on port 8080 with context-path /api/v1.
- For Windows PowerShell, use `$env:VAR="value"` instead of `export`.
- You can also set profile via JVM property: `java -Dspring.profiles.active=prod -jar ...`.
- Integration tests use Testcontainers under the `test` profile:

```bash
mvn test
```
