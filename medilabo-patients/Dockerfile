# STAGE 1: Build JAR
FROM maven:3.6.3-openjdk-17 AS build
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn -f pom.xml clean package

# STAGE 2: Initialize MSSQL Database
FROM mcr.microsoft.com/mssql/server:latest
RUN mkdir -p /docker-entrypoint-initdb.d
COPY --from=build /app/target/*.jar /app/medilabo-patients.jar
COPY init-mssql.sql /docker-entrypoint-initdb.d/init-mssql.sql
CMD ["bash", "-c", "/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P $SA_PASSWORD -d $MSSQL_DB -i /docker-entrypoint-initdb.d/init-mssql.sql"]

# STAGE 3: Run the Spring Boot Application
FROM openjdk:15
COPY --from=build /app/target/*.jar /app/medilabo-patients.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","/app/medilabo-patients.jar"]
