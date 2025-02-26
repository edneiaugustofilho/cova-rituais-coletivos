FROM maven:3.9.9-eclipse-temurin-23-alpine AS build

COPY . .

RUN mvn package -DskipTests

FROM amazoncorretto:23.0.2-al2023-headless

COPY --from=build target/collectiverituals.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
