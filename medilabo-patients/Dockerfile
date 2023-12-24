# STAGE 1: Build JAR
FROM maven:3.6.3-openjdk-17 AS build
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -f pom.xml clean package

# STAGE 2: Run the Spring Boot Application
FROM openjdk:15
COPY --from=build /app/target/*.jar /app/medilabo-patients.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","/app/medilabo-patients.jar"]
