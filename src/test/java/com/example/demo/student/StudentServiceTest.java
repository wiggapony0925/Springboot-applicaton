package com.example.demo.student;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.Month.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
        studentService = new StudentService(studentRepository);
    }

    @Test
    void getStudents() {
        // Arrange
        List<Student> expectedStudents = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> actualStudents = studentService.getStudents();

        // Assert
        assertSame(expectedStudents, actualStudents);
    }

    Long studentId = 34L;
    @Test
    void addNewStudent() {
        // Arrange

        Student newStudent = new Student(studentId,  "jeffrey", "john@example.com", LocalDate.of(2000, SEPTEMBER, 25), Student.Gender.MALE);
        when(studentRepository.findStudentByEmail(newStudent.getEmail())).thenReturn(Optional.empty());

        // Act
        studentService.addNewStudent(newStudent);

        // Assert
        Mockito.verify(studentRepository, Mockito.times(1)).save(newStudent);
    }

    @Test
    void addNewStudentWithTakenEmail() {
        // Arrange
        Student existingStudent = new Student(studentId,  "jeffrey", "john@example.com", LocalDate.of(2000, SEPTEMBER, 25), Student.Gender.MALE);
        when(studentRepository.findStudentByEmail(existingStudent.getEmail())).thenReturn(Optional.of(existingStudent));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> studentService.addNewStudent(existingStudent));
    }

    @Test
    void deleleteStudent() {
        // Arrange
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(true);

        // Act
        studentService.deleleteStudent(studentId);

        // Assert
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(studentId);
    }

    @Test
    void deleleteNonExistingStudent() {
        // Arrange
        Long studentId = 2L;
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> studentService.deleleteStudent(studentId));
    }

    @Test
    void editStudent() {
        // Arrange
        Long studentId = 1L;
        Student existingStudent = new Student(studentId,  "jeffrey", "john@example.com", LocalDate.of(2000, SEPTEMBER, 25), Student.Gender.MALE);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));

        // Act
        studentService.editStudent(studentId, "UpdatedName", "updated@example.com");

        // Assert
        assertEquals("UpdatedName", existingStudent.getName());
        assertEquals("updated@example.com", existingStudent.getEmail());
    }

    @Test
    void editNonExistingStudent() {
        // Arrange
        Long studentId = 2L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> studentService.editStudent(studentId, "UpdatedName", "updated@example.com"));
    }

    @Test
    void editStudentWithShortName() {
        // Arrange
        Long studentId = 1L;
        Student existingStudent = new Student(studentId,  "jeffrey", "john@example.com", LocalDate.of(2000, SEPTEMBER, 25),
                Student.Gender.MALE);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> studentService.editStudent(studentId, "J", "updated@example.com"));
    }
}
