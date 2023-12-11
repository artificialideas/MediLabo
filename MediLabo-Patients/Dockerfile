FROM adoptopenjdk/openjdk11:alpine-jre
RUN apk add --no-cache bash

COPY target/MediLabo-Patients-*.jar MediLabo-Patients.jar

COPY . .EXPOSE 9001/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/MediLabo-Patients.jar"]