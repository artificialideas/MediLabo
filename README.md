# MediLabo
Medilabo is a medical application that allows the listing of patients with an analisis of their risk of Diabetes depending on their profile and doctor's notes left after each medical visit.

## Specifications
This application has a microservice structure with Java and Typescript as core technologies.
* Medilabo-Patients: Java 15; Spring Boot 2.7.5; MicrosoftSQL Server
* Medilabo-Notes: Java 17; Spring Boot 3.2.0-SNAPSHOT; MongoDB
* Medilabo-Assessments: Java 17; Spring Boot 3.2.0-SNAPSHOT
* Medilabo-Gateway: Java 17; Spring Boot 2.7.17
* Medilabo-Frontend: Angular 16; Node 18

## Run
Medilabo uses Docker. To make it run, please follow this steps:
1. Download and install Docker from docker.com: https://www.docker.com/products/docker-desktop/
2. Once installed, run the application.
3. Open a console and navigate until you are in _/Medilabo_ folder.
4. Build all images with: `docker-compose build`
5. **First** you must run the MicrosoftSQL server container: `docker-compose up mssqldb`
6. **Second**, open your Docker CLI and type the command in _/medilabo-patients/src/main/resources/init_database.sh_
7. **Third**, run all the remaining images with: `docker-compose up -d`

The entrypoint URL for Medilabo app is: http://localhost:9000

## Green code:
Some green strategies used for Medilabo application:

### Docker Compose File:
* Use of environment variables in the docker-compose.yml file for sensitive information like database credentials and configurations.
* Separation of an .env file to store environment variables.

### Spring Boot Application Configuration:
* Externalization of the application configuration using application.yml files.
* Use of environment variables for sensitive information.

### Database Initialization:
* For MSSQL, creation of an initialization SQL script to set up the database schema and initial data.
* In Docker Compose, mount of the initialization script to `/docker-entrypoint-initdb.d/` for automatic execution during container startup.

### Docker Container Configuration:
* Set up Docker containers for each microservice and multi-stage Docker builds for efficient image creation.
* Limit user privileges to the minimum required for application functionality.

### Security Best Practices:
* Avoid hardcoding sensitive information in configuration files.
* Securely manage credentials, especially database usernames and passwords.