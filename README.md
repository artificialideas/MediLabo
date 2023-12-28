# MediLabo
Medilabo is a medical application that allows the listing of patients with an analisis of their risk of Diabetes depending on their profile and doctor's notes left after each medical visit.

## Specifications
This application has a microservice structure with Java and Typescript as core technologies.
* Medilabo-Patients: Java 15; Spring Boot 2.7.5; MicrosoftSQL Server
* Medilabo-Notes: Java 17; Spring Boot 2.7.5; MongoDB
* Medilabo-Assessments: Java 17; Spring Boot 2.7.5
* Medilabo-Gateway: Java 17; Spring Boot 2.7.17
* Medilabo-Frontend: Angular 16; Node 18

## Run
Medilabo uses Docker. To make it run, please follow this steps:
1. Download and install Docker from docker.com: https://www.docker.com/products/docker-desktop/
2. Once installed, run the application.
3. Open a console and navigate until you are in _/Medilabo_ folder.
4. Build all images with: `docker-compose build`
5. **First** you must run the MicrosoftSQL server container: `docker-compose up mssqldb`
6. **Second**, open your _Docker > Containers > mssqldb > CLI_ and type the command in _/medilabo-patients/src/main/resources/init_database.sh_
7. **Third**, run all the remaining images with: `docker-compose up -d`

The entrypoint URL for Medilabo app is: http://localhost:9000

## Green code:
"Green coding is an environmentally sustainable computing practice that seeks to minimize the energy involved in processing lines of code and, in turn, help organizations reduce overall energy consumption." (_IBM_)

Some green strategies used for Medilabo application:

### Application structure:
#### Microservices:
Microservices let developers build applications that break down complicated software into smaller elements that will be called when needded.
#### Cloud-based:
By using a cloud infrastructure, developers can cut the amount of data sent, reducing resources and energy.
#### Virtual machines:
With Docker mounting VM and containers, applications can be installed reducing the number of servers needed for them to run.

### Spring Boot Application Configuration:
* Externalization of the application configuration using application.yml files.
* Use of environment variables for sensitive information.

### Database Initialization:
* Creation of initialization SQL scripts to set up the database schema and initial data.
* In Docker Compose, mount of the initialization script to `/docker-entrypoint-initdb.d/` for automatic execution during container startup.

### Docker Compose File & Container Configuration:
* Use of environment variables in the docker-compose.yml file for sensitive information like database credentials and configurations.
* Separation of an .env file to store environment variables.
* Set up Docker containers for each microservice and multi-stage Docker builds for efficient image creation.
* Limit user privileges to the minimum required for application functionality.

### Improvements:
* Add shared service for shared Data Transfer Objects, functionalities.
* Improve docker compose industrialization with the inclusion of init_database.sh in general build.