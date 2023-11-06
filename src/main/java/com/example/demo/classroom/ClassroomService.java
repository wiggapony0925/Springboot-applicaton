package com.example.demo.classroom;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, StudentRepository studentRepository) {
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
    }

    public void createClassroom(Classroom classroom) {
        List<Classroom> classroomBySubject = classroomRepository.findBySubject(classroom.getSubject());

        if (!classroomBySubject.isEmpty()) {  /// CHANGE in order to filter by sqedule
            throw new IllegalStateException("A classroom with the same subject already exists.");
        }
        classroomRepository.save(classroom);
    }

    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }


    public boolean addStudentToClassroom(Long studentId, Long classroomId) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));

        if (classroom.getStudents().contains(student)) {
            throw new IllegalStateException("Student with ID " + studentId + " is already in this classroom");
        }

        List<Student> students = classroom.getStudents();
        students.add(student);
        classroom.setStudents(students); // Update the list of students in the classroom
        classroomRepository.save(classroom);

        return true;
    }


    public boolean removeStudentFromClassroom(Long studentId, Long classroomId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found"));

        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new EntityNotFoundException("Classroom with ID " + classroomId + " not found"));

        if (classroom.getStudents().contains(student)) {
            List<Student> students = classroom.getStudents();
            students.remove(student);
            classroom.setStudents(students);
            classroomRepository.save(classroom);
            return true;
        }

        return false;
    }

    public void deleteClassroom(Long classId) {
        boolean classExist = classroomRepository.existsById(classId);
        if (!classExist) {
            throw new IllegalStateException("the Class with the id of: " + classId + " Doesnt exist");
        }
        classroomRepository.deleteById(classId);
    }
}

