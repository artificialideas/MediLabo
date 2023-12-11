FROM node:18-alpine
WORKDIR /usr/src/app

# dependencies
COPY ["package.json", "package-lock.json", "./"]
RUN npm install

# source
#COPY ./src ./src
#COPY ./*.json ./
COPY . .EXPOSE 4200/
#tells Docker what port the application is listening on for traffic

RUN chown -R node /usr/src/app
USER node

CMD ["npm", "start"]
USER node
