#Source image with :
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the jar from local machine into image to build
COPY target/*.jar medilabo-gateway.jar

#Port
EXPOSE 9000

# Define command to run java application
ENTRYPOINT ["java", "-jar", "medilabo-gateway.jar"]