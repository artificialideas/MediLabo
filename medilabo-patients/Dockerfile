#Source image with :
FROM openjdk:15

#Copy and add from source to the image filesystem
COPY target/*.jar medilabo-patients-1.0-SNAPSHOT.jar

#Port
EXPOSE 9001

# Define command to run java application
ENTRYPOINT ["java", "-jar", "medilabo-patients-1.0-SNAPSHOT.jar"]