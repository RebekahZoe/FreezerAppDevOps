#!/bin/bash
root=root

rm -rf FreezerApplication

git clone -b docker https://github.com/RebekahZoe/FreezerApplication.git

cd FreezerApplication

touch Dockerfile 

echo ' # build from the Maven image
# which has a maven environment configured already
FROM maven:latest
# copy our application in
COPY . /FreezerApplication
# change the working directory to where we are building
# the application
WORKDIR /FreezerApplication
# use maven to build the application
RUN mvn clean package
# create a new build stage from the Java image
# which has java installed already
FROM openjdk:8-jdk-alpine
# change the working directory to where the application
# is going to be installed
WORKDIR /FreezerApplication
# copy the JAR file that was created in the previous
# build stage to the application folder in this build stage
COPY --from=0 /FreezerApplication/target/*.jar app.jar
# create an entrypoint to run the application
ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]' > Dockerfile

docker stop mysql

docker stop freezerapp

docker system prune -a 

docker network create freezer-network

docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

docker network connect freezer-network mysql

sleep 30s

docker container run -it --network freezer-network --rm mysql mysql -hmysql -u root -proot -e "create database freezer_database;" 

docker build -t freezer-app .

docker run --name freezerapp --network freezer-network -d -p 9090:8082 freezer-app
