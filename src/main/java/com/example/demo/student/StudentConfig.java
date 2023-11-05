package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

// static removes the Month.SEPTEMBER into just SEPTEMBER s

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
           Student jeffrey = new Student(1L,
                   "Jeffrey Fernandez",
                   "ninjeff06@gmail.com",
                   LocalDate.of(2000, SEPTEMBER, 25),
                   Student.Gender.MALE);
           
           Student ryan = new Student(3L,
                   "Ryan dope",
                   "ryan@gmail.com",
                   LocalDate.of(2010, SEPTEMBER, 24),
                   Student.Gender.MALE);

           repository.saveAll(List.of(jeffrey,ryan));
        };
    }
}
