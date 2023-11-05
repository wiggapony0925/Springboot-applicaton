package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.Month.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void itShouldFindStudentByEmail() {
        // Given
        Student student = new Student(6L,"Jeffrey", "jeffrey@gmail.com", LocalDate.of(2000, SEPTEMBER, 25),
                Student.Gender.MALE);
        studentRepository.save(student);

        // When
        Optional<Student> foundStudent = studentRepository.findStudentByEmail("jeffrey@gmail.com");

        // Then
        assertEquals(student, foundStudent.orElse(null));
    }
}
