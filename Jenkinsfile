pipeline {
  agent any
  stages {
    
   stage('----mvn clean----'){
    steps{
      sh "mvn clean"
    }
   }
    
    stage('----mvn test----'){
    steps{
      sh "mvn test"
    }
    }
    
    stage('----mvn package----'){
    steps{
      sh "mvn package -DskipTests"
    }
   }
    stage('----build dockerfile----'){
      steps{
	      sh " touch Dockerfile"
        sh """echo '# build from the Maven image
# which has a maven environment configured already
FROM maven:latest
# copy our application in
COPY . /FreezerAppDevOps
# change the working directory to where we are building
# the application
WORKDIR /FreezerAppDevOps
# use maven to build the application
RUN mvn clean package -DskipTests
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
ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]' > Dockerfile"""
      }
    }
    
    stage('----Build Image For Application----'){
    steps{
      sh "docker build -t rebekahzoe/freezerappbe:$BUILD_NUMBER ."
    }
   }
   stage('----Push to dockerhub----'){
	   steps{
		withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
		sh "docker push rebekahzoe/freezerappbe:$BUILD_NUMBER"
		}
	   }
   }
	  stage('----nexus----'){
		  steps{
			  sh "mvn deploy"
		  }
	  }
	  stage('----adding build number----'){
		  steps{
			  withCredentials([usernamePassword(credentialsId: "freezerTest.pem", usernameVariable: "", passwordVariable: "")]) {
			  sh "ssh -i 'freezerTest.pem' ubuntu@ec2-35-176-215-25.eu-west-2.compute.amazonaws.com -y"
			  sh "export BuildNumber = $BUILD_NUMBER"
			  }
		  }
	  }
 }
}
