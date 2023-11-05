package com.example.demo;

import com.example.demo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.MalformedParametersException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
@RestController //return joson
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping
	public String helloApplication() {
		return "<h1>Hello Application, backend</h1>";
	}

	@GetMapping("/student")
	public List<Student> displayStudents() {
		return List.of(
				new Student(1L, "Jeffrey Fernandez", "ninjeff06@gmail.com", 17, LocalDate.of(2000, Month.SEPTEMBER, 25), Student.Gender.MALE)
		);
	}
}
