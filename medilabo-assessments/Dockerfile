#Source image with :
FROM amazoncorretto:17

# Copy the jar from local machine into image to build
COPY target/*.jar medilabo-assessments-1.0-SNAPSHOT.jar

#Port
EXPOSE 9003

# Define command to run java application
ENTRYPOINT ["java", "-jar", "medilabo-assessments-1.0-SNAPSHOT.jar"]