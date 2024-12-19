# Étape 1 : Construire le projet
FROM maven:3.8.8-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/target/reservation-management-0.0.1-SNAPSHOT.jar /app/reservation-management.jar
ENTRYPOINT ["java", "-jar", "reservation-management.jar"]
