FROM node:18-alpine

WORKDIR /usr/src/app
COPY ["package.json", "package-lock.json", "./"]
RUN npm install

COPY . .
EXPOSE 4200

RUN chown -R node /usr/src/app
USER node
CMD ["ng", "serve"]

# ng serve --host=127.0.0.1
