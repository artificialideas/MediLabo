#Source image with :
FROM openjdk:15

# Set the working directory in the container
WORKDIR /app

#Copy and add from source to the image filesystem
COPY target/*.jar medilabo-patients.jar

#Port
EXPOSE 9001

# Define command to run java application
ENTRYPOINT ["java", "-jar", "medilabo-patients.jar"]