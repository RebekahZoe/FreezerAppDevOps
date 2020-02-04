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
   
 }
}
