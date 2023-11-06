package com.example.demo.classroom;

import com.example.demo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findBySubject(String subject);
    @Query("SELECT c.students FROM Classroom c WHERE c.subject = :subject")
    List<Student> findStudentsBySubject(@Param("subject") String subject);

}

