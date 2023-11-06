package com.example.demo.classroom;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Random;

@Configuration
public class ClassroomConfig {
    @Bean
    CommandLineRunner classroomCommandLineRunner(ClassroomRepository repository, StudentRepository studentRepository) {
        return args -> {
            List<Student> students = studentRepository.findAll();
            int numStudents = students.size();

            Classroom mathClass = new Classroom(
                    "Math Class",
                    "Professor Math",
                    getRandomCredits(),
                    getRandomRoomNumber(),
                    students.subList(0, numStudents / 3),
                    "Mathematics"
            );

            Classroom historyClass = new Classroom(
                    "History Class",
                    "Professor History",
                    getRandomCredits(),
                    getRandomRoomNumber(),
                    students.subList(numStudents / 3, 2 * numStudents / 3),
                    "History"
            );

            Classroom scienceClass = new Classroom(
                    "Science Class",
                    "Professor Science",
                    getRandomCredits(),
                    getRandomRoomNumber(),
                    students.subList(2 * numStudents / 3, numStudents),
                    "Science"
            );

            repository.saveAll(List.of(mathClass, historyClass, scienceClass));
        };
    }
    private double getRandomCredits() {
        return new Random().nextDouble() * 4.0;
    }

    private Long getRandomRoomNumber() {
        return new Random().nextLong();
    }
}
