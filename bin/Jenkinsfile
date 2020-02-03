pipeline {
  agent any
  stages {
  // stage('----Stop Previous----'){
    //  steps{
      // sh "docker stop freezerapp "
      // sh "docker rm  freezerapp"
      //  sh "docker rmi mysql:latest freezer-app -f"
       // sh "docker network disconnect freezer-network frontend"
       // sh "docker network remove freezer-network"
       // }
     // }
   stage('----Build Image For Application----'){
    steps{
      sh "docker build -t freezer-app ."
    }
   }
   stage('----Run Container For Application----'){
    steps{
      sh "docker run --name freezerapp -d -p 9090:8082 freezer-app"
    }
  }
 }
}
