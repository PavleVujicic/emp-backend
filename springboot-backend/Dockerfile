# Use OpenJDK 11 for build and runtime
FROM openjdk:11-jdk-slim

# Install required packages
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy Maven wrapper and make it executable
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Fix permissions for Maven wrapper
RUN chmod +x ./mvnw

# Download dependencies (this layer will be cached)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests -B

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/*.jar"] 