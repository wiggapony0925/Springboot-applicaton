package com.example.demo.student;

import jakarta.transaction.Transactional;
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

    // edit students finds student first then checks if the name is longer than 2 and isnt the same
    @Transactional
    public void editStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "The student with ID " + studentId + " does not exist"));

        if (name != null) {
            if (name.length() > 2 && !name.equals(student.getName())) {
                student.setName(name);
            } else {
                throw new IllegalArgumentException("Invalid name provided.");
            }
        }

        if (email != null && !email.equals(student.getEmail())) {
            // Check if the edited email is already taken by another student
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("Email is already taken.");
            }
            student.setEmail(email);
        }

        studentRepository.save(student);
    }


}
