#!/bin/bash
# Render build script for Spring Boot

# Make Maven wrapper executable
chmod +x ./mvnw

# Clean and build the application
./mvnw clean package -DskipTests

# Move the built JAR to root directory for easy access
cp target/*.jar app.jar 