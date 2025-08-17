# ---- Build stage ----
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /workspace
COPY handoff-backend/pom.xml ./pom.xml
# Pre-fetch dependencies for faster incremental builds
RUN mvn -q -e -DskipTests dependency:go-offline
# Copy source and build
COPY handoff-backend/src ./src
RUN mvn -q -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=local
WORKDIR /app
COPY --from=builder /workspace/target/handoff-backend-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

