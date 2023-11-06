package com.example.demo.classroom;

import com.example.demo.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/classroom")
@CrossOrigin(origins = "http://localhost:3001") // Specify the allowed origin (your React app's origin)
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }



    // GET
    @GetMapping
    public List<Classroom> getClassrooms() {
        return classroomService.getClassrooms(); // Use classroomService instead of studentService
    }

    //CREATE A CLASS
    @PostMapping
    public ResponseEntity<String> createNewClassroom(@RequestBody Classroom classroom) {
        classroomService.createClassroom(classroom);

        String successMessage = "Created successfully: " + classroom.getClassName();
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);

    }
    //add student to class
    @PostMapping("/{classroomId}/{studentId}")
    public ResponseEntity<String> addStudentToClassroom(@PathVariable Long classroomId, @PathVariable Long studentId) {
        if (classroomService.addStudentToClassroom(studentId, classroomId)) {
            return ResponseEntity.ok("Student added to classroom successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add student to classroom. Student or classroom not found.");
        }
    }
    // REMOVE A Student
    @DeleteMapping ("/{classroomId}/{studentId}")
    public ResponseEntity<String> deleteStudentToClassroom(@PathVariable Long classroomId, @PathVariable Long studentId) {
        if (classroomService.removeStudentFromClassroom(studentId, classroomId)) {
            return ResponseEntity.ok("Student removed to classroom successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to remove student to classroom. Student or classroom not found.");
        }
    }


    // DELETE A CLASS
    @DeleteMapping(path = "{classId}")
    public void deleteClassroom(@PathVariable("classId") Long ClassId) {
        classroomService.deleteClassroom(ClassId);
    }


    // EDIT CLASS

    // GET ALL STUDENTS FROM CLASS



}


