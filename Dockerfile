FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -DskipTests

FROM openjdk:17
ARG APP_ENV
ENV APP_ENV=$APP_ENV
WORKDIR /app
COPY --from=builder /app/target/business-profile-service-0.0.1.jar app.jar
CMD java -Dspring.profiles.active=$APP_ENV -jar app.jar

