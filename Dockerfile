FROM ubuntu:latest
LABEL authors="Yesid"

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/PosApp-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "PosApp-0.0.1-SNAPSHOT.jar"]