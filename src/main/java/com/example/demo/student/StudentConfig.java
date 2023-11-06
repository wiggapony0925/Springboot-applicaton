package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository repository) {
        return args -> {
            Student jeffrey = new Student(
                    "Jeffrey Fernandez",
                    "ninjeff06@gmail.com",
                    LocalDate.of(2000, SEPTEMBER, 25),
                    Student.Gender.MALE
            );

            Student ryan = new Student(
                    "Ryan dope",
                    "ryan@gmail.com",
                    LocalDate.of(2010, SEPTEMBER, 24),
                    Student.Gender.MALE
            );

            Student alice = new Student(
                    "Alice Smith",
                    "alice@gmail.com",
                    LocalDate.of(2001, JANUARY, 15),
                    Student.Gender.FEMALE
            );

            Student john = new Student(
                    "John Doe",
                    "john@example.com",
                    LocalDate.of(1995, MARCH, 10),
                    Student.Gender.MALE
            );

            Student emily = new Student(
                    "Emily Johnson",
                    "emily@example.com",
                    LocalDate.of(1998, JULY, 8),
                    Student.Gender.FEMALE
            );

            Student daniel = new Student(
                    "Daniel Brown",
                    "daniel@example.com",
                    LocalDate.of(1999, MAY, 20),
                    Student.Gender.MALE
            );

            Student lisa = new Student(
                    "Lisa Davis",
                    "lisa@example.com",
                    LocalDate.of(2002, DECEMBER, 5),
                    Student.Gender.FEMALE
            );

            Student michael = new Student(
                    "Michael Wilson",
                    "michael@example.com",
                    LocalDate.of(2003, AUGUST, 30),
                    Student.Gender.MALE
            );

            Student sarah = new Student(
                    "Sarah Lee",
                    "sarah@example.com",
                    LocalDate.of(1997, OCTOBER, 18),
                    Student.Gender.FEMALE
            );

            Student matthew = new Student(
                    "Matthew Taylor",
                    "matthew@example.com",
                    LocalDate.of(2004, FEBRUARY, 9),
                    Student.Gender.MALE
            );

            Student olivia = new Student(
                    "Olivia Harris",
                    "olivia@example.com",
                    LocalDate.of(1996, JUNE, 14),
                    Student.Gender.FEMALE
            );

            Student ethan = new Student(
                    "Ethan Martinez",
                    "ethan@example.com",
                    LocalDate.of(1994, APRIL, 22),
                    Student.Gender.MALE
            );

            repository.saveAll(List.of(jeffrey, ryan, alice, john, emily, daniel, lisa, michael, sarah, matthew, olivia, ethan));
        };
    }
}
