package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {
    @GetMapping
    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "Jeffrey Fernandez", "ninjeff06@gmail.com", 17, LocalDate.of(2000, Month.SEPTEMBER, 25), Student.Gender.MALE)
        );
    }
}
