package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // SELECT
    Optional<Student> findStudentByEmail(String email);

    // get classroom of student
    @Query("SELECT s, COUNT(c) FROM Student s LEFT JOIN s.classrooms c GROUP BY s.id")
    List<Object[]> findStudentsWithClassCounts();
}
