package com.example.demo.student;

import com.example.demo.classroom.Classroom;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Gender gender;

    @ManyToMany(mappedBy = "students", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Classroom> classrooms;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
    }

    public Student(String name, String email, LocalDate dob, Gender gender) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }
}
