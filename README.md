# RESTful Services with Spring Boot

This project demonstrates a basic implementation of RESTful services using Spring Boot. The project contains a REST controller (RestTestController.java) that provides endpoints to perform CRUD operations on a Student resource.

# Usage with Postman
You can use Postman to test the different endpoints provided by the RestTestController. The available endpoints are:

GET /students: Returns a list of all students.
GET /students/{id}: Returns a specific student with the given ID.
POST /students: Adds a new student.
PUT /students/{id}: Updates an existing student with the given ID.
DELETE /students/{id}: Deletes a student with the given ID.
DELETE /students: Deletes all students.

To test these endpoints using Postman, follow these steps:

Open Postman and create a new request.
Set the request method to the desired HTTP method (GET, POST, PUT, DELETE).
Set the request URL to http://localhost:8080/{endpoint}, where {endpoint} is one of the endpoints listed above.
If necessary, add any required parameters or request body.
Click the "Send" button to send the request.

# Testing with JUnit

The RestTestController class also contains JUnit tests to verify that the REST endpoints are working as expected. The tests perform the following operations:

Test that the GET /students endpoint returns a non-empty list of students.
Test that the GET /students/{id} endpoint returns the expected student for a given ID.
Test that the GET /students/{id} endpoint returns an empty string when given an ID that does not exist.
Test that the POST /students endpoint adds a new student to the database.
Test that the PUT /students/{id} endpoint updates an existing student in the database.
Test that the DELETE /students/{id} endpoint deletes a student from the database.
Test that the DELETE /students endpoint deletes all students from the database.

To run these tests, you can execute mvn test in the root directory of the project. The tests will be executed automatically and the results will be displayed in the console.
