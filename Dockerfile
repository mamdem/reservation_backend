FROM eclipse-temurin:17

LABEL mentainer="barkhama36@gmail.com"

WORKDIR /app

COPY target/reservation-management-0.0.1-SNAPSHOT.jar /app/reservation-management.jar

ENTRYPOINT ["java", "-jar", "reservation-management.jar"]
