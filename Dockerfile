# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17-jdk-slim

# Install python3 and pip
RUN apt-get update && apt-get install -y python3 python3-pip && apt-get clean

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/divide-ai-backend-0.0.1-SNAPSHOT.jar ./app.jar

# Copy Python files & requirements
COPY scrapper /app/scrapper

# Install python dependencies
RUN pip3 install -r scrapper/requirements.txt

# Start the app
CMD ["java", "-jar", "app.jar"]
