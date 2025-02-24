# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable jar file to the container
COPY target/product-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set environment variables for different environments
# These can be overridden by passing them during runtime or in Kubernetes deployment files
ENV SPRING_PROFILES_ACTIVE=default

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
