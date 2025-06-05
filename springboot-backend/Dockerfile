# Multi-stage build for Spring Boot on Google Cloud Run
FROM openjdk:11-jdk-slim as build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set working directory
WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM openjdk:11-jre-slim

# Create app user for security
RUN useradd -r -s /bin/false spring

# Set working directory
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Change ownership to app user
RUN chown spring:spring app.jar

# Switch to app user
USER spring

# Expose port (Cloud Run will set PORT environment variable)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"] 