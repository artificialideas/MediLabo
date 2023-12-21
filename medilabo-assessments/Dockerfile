#Source image with :
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the jar from local machine into image to build
COPY target/medilabo-assessments-*.jar /medilabo-assessments-1.0-SNAPSHOT.jar

#Port
EXPOSE 9003

# Define command to run java application
ENTRYPOINT ["java", "-jar", "/medilabo-assessments-1.0-SNAPSHOT.jar"]