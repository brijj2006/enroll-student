# enroll-student

develop REST API for student enrolment project for a school

- [Retrieve all students          - GET     /fetchStudents](#retrieve-all-students)
- [Enroll a student               - POST    /students](#enroll-a-student)
- [Retrieve student by class      - GET     /fetchStudents/class/{className} -> /fetchStudents/class/1A](#retrieve-student-by-class)
- [Retrieve student by Id         - GET     /fetchStudents/id/{id} -> /fetchStudents/id/1000](#retrieve-student-by-id)
- [Delete a student               - DELETE  /students](#delete-a-student)
- [Update a student               - UPDATE  /students](#update-a-student)



## Coverage

- Send JSON as POST, PUT, DELETE and GET requests to REST endpoints
- SpringBoot implementation to develop rest api
- Used JPA-H2 DB for persisting data 
- Pre-populating few of the student data in H2 DB 
- Exception handling for few invalid requests
- BDD tests in Cucumber (GET/POST methods) : Included both positive and negative scenarios
- Reporting for Cucumber
- Used Logger - needs more logging
- REST API documentation using Swagger
- REST API health check using SpringBoot Actuator
- CI/CD : Github Actions integration for automated BDD test trigger on each pull and merge request to master
(Tests failed since the automated application deployment was incomplete)


## Steps to trigger BDD tests

- There are five diff ways to trigger the tests

1. Go to Github Actions -> Workflows -> Java CI with Maven -> Run Workflow
2. Tests will be automatically tiggerred on every pull request 
3. Tests will be automatically triggerred on every merge on master
4. Manually right click on RunTest (Cucumber-JUnit runner) and run the BDD tests
5. Run command on CLI : mvn test


![image](https://user-images.githubusercontent.com/50976445/140659125-5c7cd225-2793-41a4-90dd-be505d9fcbe4.png)


Cucumber reports can be found on  : reports.cucumber.io portal
The link can be captured from the log terminal once the tests complete.
Eg. https://reports.cucumber.io/reports/356db68d-e692-452b-b8c4-d584691db8a5
![image](https://user-images.githubusercontent.com/50976445/140659092-4642d897-8f6f-4ff4-9d5f-f5f066e358de.png)



Passed Report
![image](https://user-images.githubusercontent.com/50976445/140659036-98f3f6e6-eb50-4474-86a8-834de926097b.png)

Failed Report
![image](https://user-images.githubusercontent.com/50976445/140659068-0023b8cb-6ee2-4c0e-b842-e8344c0595c2.png)


Swagger API Doc
![image](https://user-images.githubusercontent.com/50976445/140671491-5e0ffe4e-8499-4edb-8c66-dfb8988734aa.png)


## API

# Retrieve all students          

- GET     /fetchStudents

Request
```
http://localhost:8080/fetchStudents
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

# Retrieve student by class      
- GET     /fetchStudents/class/{className}

Request
```
http://localhost:8080//fetchStudents/class/1A
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

# Retrieve student by Id         
- GET     /fetchStudents/id/{id}

Request
```
http://localhost:8080//fetchStudents/id/1000
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

# Enroll a student               
- POST    /students

Request
```
http://localhost:8080/students
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
200 OK
```


# Update a student               
- UPDATE  /students

Request
```
http://localhost:8080/students
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
200 OK
```

# Delete a student               
- DELETE  /students

Request
```
http://localhost:8080/students
```
```json
{
    "id": 2
}
```

Response
```
200 OK
```



## To-Do

- Fix bugs in update/post api calls
- Tests for update/delete api calls
- More test coverage (field validations and workflows)
- Automated application deployment
- Code coverage - SonarQube integration
- Unit test cases



## References

Swagger API Docs : 

http://localhost:8080/v2/api-docs

http://localhost:8080/swagger-ui.html

Springboot actuator (Health check for the API's) :

http://localhost:8080/actuator

