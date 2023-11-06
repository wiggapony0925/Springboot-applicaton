package com.example.demo.classroom;

import com.example.demo.student.Student;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String professor;
    private double Credits;
    private Long roomNumber;

    @ManyToMany
    @JoinTable(name = "classroom_student",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    private String subject;

    // Default constructor with no arguments
    public Classroom() {
    }

    // Constructor with arguments
    public Classroom(Long id, String className, String professor, double credits, Long roomNumber, List<Student> students, String subject) {
        this.id = id;
        this.className = className;
        this.professor = professor;
        Credits = credits;
        this.roomNumber = roomNumber;
        this.students = students;
        this.subject = subject;
    }

    // Constructor without ID
    public Classroom(String className, String professor, double credits, Long roomNumber, List<Student> students, String subject) {
        this.className = className;
        this.professor = professor;
        Credits = credits;
        this.roomNumber = roomNumber;
        this.students = students;
        this.subject = subject;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public double isCredits() {
        return Credits;
    }

    public void setCredits(double credits) {
        Credits = credits;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Other fields and methods
    public static long getTotalNumberOfClassrooms(EntityManager entityManager) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Classroom c", Long.class);
        return query.getSingleResult();
    }
}


