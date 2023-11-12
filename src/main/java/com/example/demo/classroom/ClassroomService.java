package com.example.demo.classroom;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Optional<Classroom> getClassroomById(Long classId) {
        return classroomRepository.findById(classId);
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

    public void editClassroom(Long classroomId, String className, String professor, Long roomNumber, String subject) {
        // logic for editing classrooms check if the classroom exist in the db
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new IllegalStateException(
                        "The student with ID " + classroomId + " does not exist"));
        if (className != null) {
            if (className.length() > 2 && !className.equals(classroom.getClassName())) {
                classroom.setClassName(className);
            } else {
                throw new IllegalStateException("Invalid name provided.");
            }
        }

        if (professor != null) {
            if (professor.length() > 2 && !professor.equals(classroom.getProfessor())) {
                classroom.setProfessor(professor);
            } else {
                throw new IllegalStateException("Invalid name provided. ");
            }
        }

        if (roomNumber != null) {
            if (roomNumber.describeConstable().isPresent() && roomNumber.equals(classroom.getRoomNumber())) {
                classroom.setRoomNumber(roomNumber);
            } else {
                throw new IllegalStateException("Invalid Room Number provided");
            }
        }

        if (subject != null) {
            if (subject.length() > 2 && !subject.equals(classroom.getSubject())) {
                classroom.setSubject(subject);
            } else {
                throw new IllegalStateException("Invalid Subject for " + className);
            }

        }

        classroomRepository.save(classroom);


    }

}


