package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    //adding student logic
    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);

    }

    //deleting student logic
    public void deleleteStudent(Long studentId) {
        boolean studentExist = studentRepository.existsById(studentId);
        if (!studentExist) {
            throw new IllegalStateException("The student with this id" + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);


    }
}
