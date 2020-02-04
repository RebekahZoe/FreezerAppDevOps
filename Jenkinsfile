pipeline {
  agent any
  stages {
    
 //   stage('----mysql----'){
    //  steps{
     //   sh "docker network create freezer-network"

      //  sh "docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest"

       // sh "docker network connect freezer-network mysql"

      //  sh "sleep 30s"

       // sh "docker container run -it --network freezer-network --rm mysql mysql -hmysql -u root -proot -e 'create database freezerdb;'"
      //}
    //}

    stage('---- Do Dockerfile----'){
      steps{
        sh " touch Dockerfile"
        sh """echo ' # build from the Maven image
            # which has a maven environment configured already
            FROM maven:latest
            # copy our application in
            COPY . /FreezerAppDevOps
            # change the working directory to where we are building
            # the application
            WORKDIR /FreezerAppDevOps
            # use maven to build the application
            RUN mvn clean package 
            # create a new build stage from the Java image
            # which has java installed already
            FROM openjdk:8-jdk-alpine
            # change the working directory to where the application
            # is going to be installed
            WORKDIR /FreezerAppDevOps
            # copy the JAR file that was created in the previous
            # build stage to the application folder in this build stage
            COPY --from=0 /FreezerAppDevOps/target/*.jar app.jar
            # create an entrypoint to run the application
            ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]' > Dockerfile """
      }
    }
    
   stage('----Build Image For Application----'){
    steps{
      sh "docker build -t freezer-app ."
    }
   }
   
 }
}
