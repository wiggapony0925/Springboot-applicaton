package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/student")
@CrossOrigin(origins = "http://localhost:3001") // Specify the allowed origin (your React app's origin)
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    //post add a new student
    @PostMapping
    public ResponseEntity<String> registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);

        String successMessage = "Created successfully: " + student.getName();
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);

    }
    //left of on creating delete method of the controller and business logic in service
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long StudentId) {
        studentService.deleleteStudent(StudentId);

    }

    // Put or Edit
    @PutMapping(path = "{studentId}")
    public void editStudent(
            @PathVariable("studentId") Long studentId,
            @RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        String email = requestBody.get("email");
        studentService.editStudent(studentId, name, email);
    }

}