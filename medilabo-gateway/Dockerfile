### STAGE 1: Build ###
FROM maven:3.9.5-amazoncorretto-17 AS build
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -f pom.xml clean package

### STAGE 2: Run ###
FROM amazoncorretto:17-al2-generic-jdk
COPY --from=build /app/target/*.jar medilabo-gateway.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","medilabo-gateway.jar"]