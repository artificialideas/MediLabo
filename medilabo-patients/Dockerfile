### STAGE 1: Build ###
FROM maven:3.6.3-openjdk-17 AS build
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -f pom.xml clean package

#MSSQL INIT
FROM mcr.microsoft.com/mssql/server:latest
# Create a directory to hold initialization scripts
RUN mkdir -p /docker-entrypoint-initdb.d
# CMD specifies the command to run when the container starts
CMD ["bash", "-c", "/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P $SA_PASSWORD -d $MSSQL_DB -i /docker-entrypoint-initdb.d/init-mssql.sql"]

### STAGE 2: Run ###
FROM openjdk:15
COPY --from=build /app/target/*.jar medilabo-patients.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","medilabo-patients.jar"]