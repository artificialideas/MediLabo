### STAGE 1: Build ###
FROM maven:3.9.5-amazoncorretto-17 AS build
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -f pom.xml clean package

#MONGO INIT
FROM mongo:latest
# Create a directory to hold initialization scripts
RUN mkdir -p /docker-entrypoint-initdb.d
# Copy the initialization script into the container
COPY init-mongo.js /docker-entrypoint-initdb.d/
# Make the script executable
RUN chmod +x /docker-entrypoint-initdb.d/init-mongo.js


### STAGE 2: Run ###
FROM amazoncorretto:17-al2-generic-jdk
COPY --from=build /app/target/*.jar medilabo-notes.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","medilabo-notes.jar"]