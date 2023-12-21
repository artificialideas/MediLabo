FROM node:18-alpine

WORKDIR /app
# Copy the package.json and package-lock.json files into the container (they contain the dependencies to install)
#COPY ./medilabo-frontend/package*.json /app/
COPY ["package.json", "package-lock.json", "./"]

# Install Angular CLI and dependencies
RUN npm install -g @angular/cli && npm install

# Copy the rest of the application code into the container
COPY ./medilabo-frontend/ /app/

EXPOSE 4200

# Run the application when the container starts
CMD ["ng", "serve", "--host", "127.0.0.1"]
