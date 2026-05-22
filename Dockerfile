# Maven + Java 17 image
FROM maven:3.9.6-eclipse-temurin-17

# Working directory
WORKDIR /app

# Copy entire project
COPY . .

# Run Cucumber tests
CMD ["mvn", "clean", "test"]