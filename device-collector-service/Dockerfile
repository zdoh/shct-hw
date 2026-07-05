FROM gradle:9.1.0-jdk25 AS build
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradle.properties ./

COPY src ./src

RUN gradle clean bootJar --no-daemon -x test

FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]