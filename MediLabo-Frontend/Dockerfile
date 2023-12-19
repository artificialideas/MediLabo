FROM node:18-alpine

# dependencies
COPY ["package.json", "package-lock.json", "./"]
RUN npm install

EXPOSE 4200
#tells Docker what port the application is listening on for traffic

CMD ["npm", "start"]
