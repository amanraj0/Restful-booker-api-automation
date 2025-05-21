
# Restful booker API automation

Restful booker is a rest api to do all the curd operations like , creating, updating and deleting endpoints.




## Project Architecture

This project uses Service Oriented Architecture, where there is a Base service class where all the initialization happens and functions for all the reusable HTTP.
Each Controller has a separate class which extends the Base Service class and contains methods for hitting the endpoint for that controller.

## Design Pattern
In this project we are using serialization to create API request body(payload), So to ease the creation of these objects we are using Builder Design pattern.
## Pre-requisite

    1. Java 21
    2. Maven 3.9.9
    
## Folder Structure

    src/main/java: Contains the functional code to hit api
    
    src/test/java: Contains test classes and test cases.

    src/test/resources: Contains test suites xml.

    pom.xml: Maven project configuration file

## Building and Running Application

    1. Clone the repository
        https://github.com/amanraj0/Restful-booker-api-automation.git

    2. Navigate to the project folder:
        cd Restful-booker-api-automation

    3. Execute
        mvn test -P{profileName} -Dsuite={suiteName}
