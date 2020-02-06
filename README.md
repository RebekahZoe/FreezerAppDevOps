# FreezerAppDevOps

## Table of Contents

 * [Deployment](#dep)
   * [AWS](#AWS)
   * [Git](#Git)
   * [Jenkins](#Jenkins)
   * [Docker/DockerHub](#Docker)
   * [CD Pipeline](#Pipeline)

<a name="dep"></a>
## Deployment

<a name="AWS"></a>
### AWS
We created 3 IAM Users all following the principal of least privilidged.

We created 4 EC2 Instances:
* Back-End Testing
* Front-End Testing
* Back-End Production
* Front-End Production

Created an RDS MySQL Database for the production environment.

We created our own VPC with Subnets in the London Region

We created a custom security group called WebServerSG where you can only SSH into the VM's or the Database from either our own IP or from our Jenkins VM and only required ports opened.

<a href="https://ibb.co/KhtZZg2"><img src="https://i.ibb.co/tK0ff6q/Untitled-Diagram.png" alt="Untitled-Diagram" border="0"></a>

<a name="Git"></a>
### Git
We started off by creating three repos:
* Back-End (this one)
* [Front-End](https://github.com/RebekahZoe/FreezerAppFEDevOps)
* [Selenium Tests](https://github.com/lukeharrison95/devselenium)

The Back-End and Front-End Repos have a minimum of two branches: Dev and Master, Dev is for testing and Master is for production.

To merge into the master branch, two people must review the code to ensure that any code going into production is quality.

<a name="Jenkins"></a>
### Jenkins
We were provided with a EC2 Instance on which we installed Jenkins.

We then created four pipelines.

Back-End Dev Pipeline - It ran when a commit had been made to git, would do a mvn clean test package to ensure it passes all tests, it will then dockerise the application into an image and uploads it to DockerHub and then the Jar file from the mvn package is uploaded to Nexus. The final step is to SSH into the Testing Environment for the Back-End and pull down the most recent image from DockerHub and run the container.

Front-end Dev Pipeline - It ran when either the Back-End Dev Pipeline had been completed successfully or when a commit had been made to git, this dockerises the application and uploads it to DockerHub. It then SSH's into the Testing Environment for the Front-End and pull down the most recent image from DockerHub and run the container. It will then run selenium tests and the pipeline will only pass if the selenium tests pass.

Back-End Prod Pipeline - This copies the Dev Pipeline until it reaches the environments where it instead SSH's into the production environment.

Front-End Prod Pipeline - This copies the Dev Pipeline until it reaches the environments where it instead SSH's into the production environment and doesn't run the selenium tests.

<a name="Nexus"></a>
### Nexus
This is the Artifact Repository, it stores the Jar files of our successful builds.

<a name="Docker"></a>
### Docker/DockerHub
We used docker to containerise our front and back ends.

We then stored the images created by docker of our front and back ends in DockerHub.

<a name ="Databases"></a>
### Databases
We had three different styles of databases:
* H2 for testing
* MySQL (containerised) for Dev
* MySQL (RDS) for Production

The way we switched between the two MySQL style databases were via [Spring Profiles](https://www.baeldung.com/spring-profiles) (I used sections 4 and 8).

<a name="Pipeline"></a>
### CD Pipeline

<a href="https://ibb.co/xY83rkL"><img src="https://i.ibb.co/DYgLjJW/Screenshot-2020-02-06-Untitled-Diagram-drawio-draw-io-3.png" alt="Screenshot-2020-02-06-Untitled-Diagram-drawio-draw-io-3" border="0"></a>
