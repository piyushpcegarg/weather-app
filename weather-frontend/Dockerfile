# Docker Image which is used as foundation to create
# a custom Docker Image with this Dockerfile
FROM node:alpine

# A directory within the virtualized Docker environment
# Becomes more relevant when using Docker Compose later
WORKDIR /app

# Copies package.json to Docker environment
COPY package.json /app

# Installs all node packages
RUN yarn install

# Copies everything over to Docker environment
COPY . /app

# Uses port which is used by the actual application
EXPOSE 3000

# Finally runs the application
CMD ["yarn", "run", "start"]