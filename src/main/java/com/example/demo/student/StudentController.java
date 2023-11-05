package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
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

}