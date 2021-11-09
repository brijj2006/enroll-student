# enroll-student

Develop REST API's for student enrolment project of school

## Tech Stack
- Language : Java
- Framework : SpringBoot/JPA(Hibernate)
- E2E Tests : Cucumber (BDD)
- Cloud Platform : Heroku
- CI/CD : GitHub Actions/Workflows

## Coverage

- Send JSON as POST, PUT, DELETE and GET requests to REST endpoints
- SpringBoot implementation to develop rest api
- Used JPA-H2 DB for persisting data 
- Pre-populating student data in H2 DB 
- Exception handling for invalid requests
- BDD tests in Cucumber : Included both positive and negative scenarios
- Reporting for Cucumber
- Application logging
- REST API Specs using Swagger
- REST API health check using SpringBoot Actuator
- CI/CD : Github Actions integration with Heroku cloud platform and Cucumber to trigger automated deployment and regression test on each pull and merge request to master


## API Details

- [Retrieve all students          - GET     /fetchStudents](#retrieve-all-students)
- [Enroll a student               - POST    /students](#enroll-a-student)
- [Retrieve student by class      - GET     /fetchStudents/class/{className} -> /fetchStudents/class/1A](#retrieve-student-by-class)
- [Retrieve student by Id         - GET     /fetchStudents/id/{id} -> /fetchStudents/id/1000](#retrieve-student-by-id)
- [Delete a student               - DELETE  /students](#delete-a-student)
- [Update a student               - UPDATE  /students](#update-a-student)



## Steps to trigger BDD tests

- There are five diff ways to trigger the tests

1. Go to Github Actions -> Workflows -> Deploy and Test -> Run Workflow
2. Tests will be automatically tiggerred on every pull request 
3. Tests will be automatically triggerred on every merge on master
4. Manually right click on RunTest (Cucumber-JUnit runner) and run the BDD tests
5. Run command on CLI : mvn test

Automated deployment and Test using Github Actions
![image](https://user-images.githubusercontent.com/50976445/140967000-96f750b8-fa5f-4711-a61c-e69a38b47a15.png)

Automated deployment on Heroku Cloud Platform
![image](https://user-images.githubusercontent.com/50976445/140968578-e1690924-21a9-442f-8f8b-986a7c676251.png)


Cucumber reports can be found on  : reports.cucumber.io portal
The link can be captured from the log terminal once the tests complete.
Eg. https://reports.cucumber.io/reports/356db68d-e692-452b-b8c4-d584691db8a5
![image](https://user-images.githubusercontent.com/50976445/140659092-4642d897-8f6f-4ff4-9d5f-f5f066e358de.png)



Passed Report

![image](https://user-images.githubusercontent.com/50976445/140967188-437590eb-c21a-4da4-bece-302a142cf7dd.png)


![image](https://user-images.githubusercontent.com/50976445/140659036-98f3f6e6-eb50-4474-86a8-834de926097b.png)

Failed Report
![image](https://user-images.githubusercontent.com/50976445/140659068-0023b8cb-6ee2-4c0e-b842-e8344c0595c2.png)


Swagger API Doc
![image](https://user-images.githubusercontent.com/50976445/140967536-108733fb-2035-4d62-a54b-b3406c4e89ac.png)



## API

### Retrieve all students          

- GET     /fetchStudents

Request
```
https://enroll-student.herokuapp.com/fetchStudents
```
Response
```json
[
    {
        "id": 1000,
        "firstName": "Sachin",
        "lastName": "Tendulkar",
        "className": "1A",
        "nationality": "Indian"
    },
    {
        "id": 1001,
        "firstName": "Brian",
        "lastName": "Lara",
        "className": "1A",
        "nationality": "West Indian"
    },
    {
        "id": 1002,
        "firstName": "Ricky",
        "lastName": "Ponting",
        "className": "1A",
        "nationality": "Australian"
    },
    {
        "id": 1003,
        "firstName": "Virat",
        "lastName": "Kohli",
        "className": "1B",
        "nationality": "Indian"
    },
    {
        "id": 1004,
        "firstName": "Stevan",
        "lastName": "Smith",
        "className": "1C",
        "nationality": "Australian"
    }
]
```

### Retrieve student by class      
- GET     /fetchStudents/class/{className}

Request
```
https://enroll-student.herokuapp.com/fetchStudents/class/1A
```

Response
```json
[
    {
        "id": 1000,
        "firstName": "Sachin",
        "lastName": "Tendulkar",
        "className": "1A",
        "nationality": "Indian"
    },
    {
        "id": 1001,
        "firstName": "Brian",
        "lastName": "Lara",
        "className": "1A",
        "nationality": "West Indian"
    },
    {
        "id": 1002,
        "firstName": "Ricky",
        "lastName": "Ponting",
        "className": "1A",
        "nationality": "Australian"
    }
]
```

### Retrieve student by Id         
- GET     /fetchStudents/id/{id}

Request
```
https://enroll-student.herokuapp.com/fetchStudents/id/1000
```

Response
```json
{
    "id": 1000,
    "firstName": "Sachin",
    "lastName": "Tendulkar",
    "className": "1A",
    "nationality": "Indian"
}
```

### Enroll a student               
- POST    /students

Request
```
https://enroll-student.herokuapp.com/students
```
```json
{
    "firstName": "Brijendra",
    "lastName": "Singh",
    "className": "1 A",
    "nationality": "Indian"
}
```

Response
```
204 No Content
```


### Update a student               
- UPDATE  /students

Request
```
https://enroll-student.herokuapp.com/students
```
```json
{
    "id": 2,
    "className": "1 B",
    "nationality": "Indian"
}
```

Response
```
204 No Content
```

### Delete a student               
- DELETE  /students

Request
```
https://enroll-student.herokuapp.com/students
```
```json
{
    "id": 2
}
```

Response
```
204 No Content
```



## To-Do

- Tests for update/delete api calls
- More test coverage
- Code coverage - SonarQube integration
- Unit test cases



## References

Swagger API Docs : 

https://enroll-student.herokuapp.com/v2/api-docs

http://enroll-student.herokuapp.com/swagger-ui.html

Springboot actuator (Health check for the API's) :

https://enroll-student.herokuapp.com/actuator

