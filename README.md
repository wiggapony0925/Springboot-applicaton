# Student Management Backend with Spring Boot

This is a Spring Boot application for managing student records in a PostgreSQL database. It provides functionality to create, edit, and delete student data.

## Features

- **Create Students:** Add new student records with name, email, date of birth, and gender.
- **Edit Students:** Modify student details, including name and email.
- **Delete Students:** Remove students from the database.
- **List Students:** Retrieve a list of all students in the database.

## Technologies Used

- Spring Boot: Backend framework
- PostgreSQL: Database for student records
- Java: Programming language
- JPA and Hibernate: Object-relational mapping
- RESTful API: Exposes endpoints for CRUD operations
- JUnit and Mockito: For testing the application

## Usage

1. Clone the repository to your local machine.
2. Configure your PostgreSQL database in `application.properties`.
3. Build and run the Spring Boot application.
4. Access the API endpoints to manage student records.

## API Endpoints

- `POST /students`: Create a new student.
- `GET /students`: Retrieve a list of all students.
- `GET /students/{id}`: Retrieve a specific student by ID.
- `PUT /students/{id}`: Update a student's details.
- `DELETE /students/{id}`: Delete a student by ID.

## Sample Requests

### Create Student

``` json
POST /students

{
  "name": "John Doe",
  "email": "john@example.com",
  "dob": "1995-08-15",
  "gender": "MALE"
}

````

## Edit Student

```` json
PUT /students/{id}

{
  "name": "Updated Name",
  "email": "updated@example.com"
}

````

# Delete student
`DELETE /students/{studentId}`


# Tests
The application includes unit tests for the service layer using JUnit and Mockito. You can run the tests to ensure the functionality works as expected