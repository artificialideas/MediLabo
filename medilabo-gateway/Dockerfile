#Source image with :
FROM amazoncorretto:17

# Copy the jar from local machine into image to build
COPY target/*.jar medilabo-gateway-1.0-SNAPSHOT.jar

#Port
EXPOSE 9000

# Define command to run java application
ENTRYPOINT ["java", "-jar", "medilabo-gateway-1.0-SNAPSHOT.jar"]