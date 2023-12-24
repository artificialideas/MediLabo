# MediLabo

This is application uses Docker. To make it run, please follow these steps:
1. Download and install Docker from docker.com: https://www.docker.com/products/docker-desktop/
2. Once installed, run the application.
3. Open a console and navigate until you are in /Medilabo folder.
4. Build all images with: `docker-compose build`
5. **First** you must run the MicrosoftSQL server container: `docker-compose up mssqldb`
6. **Second**, open your Docker CLI and type the command in _/medilabo-patients/src/main/resources/init_database.sh_
7. **Third**, run all the remaining images with: `docker-compose up -d`

The entrypoint URL for Medilabo app is: http://localhost:9000