package com.example.demo.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/classroom")
@CrossOrigin(origins = "http://localhost:3001") // Specify the allowed origin (your React app's origin)
public class ClassroomController {

    private final ClassroomService classroomService;
    private final QRCodeGenerator qrCodeGenerator;

    @Autowired
    public ClassroomController(ClassroomService classroomService, QRCodeGenerator qrCodeGenerator) {
        this.classroomService = classroomService;
        this.qrCodeGenerator = qrCodeGenerator;
    }


    // GET
    @GetMapping
    public List<Classroom> getClassrooms() {
        return classroomService.getClassrooms(); // Use classroomService instead of studentService
    }

    @GetMapping("/{classId}")
    public Optional<Classroom> getClassroomById(@PathVariable Long classId) {
        return classroomService.getClassroomById(classId);
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

    @PutMapping(path = "{classId}")
    public void editClassroom(@PathVariable("classId") Long classId,
                              @RequestBody Map<String, String> requestBody) {
        // varibles from request body to edit and then
        String className = requestBody.get("className");
        String professor = requestBody.get("professor");
        Long roomNumber = Long.valueOf(requestBody.get("roomNumber"));
        String subject = requestBody.get("subject");
        classroomService.editClassroom(classId, className, professor, roomNumber, subject);


    }

    // Generate Classroom QrCode

    @GetMapping("/generateQRCode/{classId}")
public ResponseEntity<String> generateQRCode(@PathVariable Long classId) {
    Optional<Classroom> classroomOptional = classroomService.getClassroomById(classId);

    if (classroomOptional.isPresent()) {
        Classroom classroom = classroomOptional.get();
        boolean success = QRCodeGenerator.generateQRCode(classroom);

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body("QR Code generated for Classroom ID: " + classId);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating QR Code for Classroom ID: " + classId);
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found with ID: " + classId);
    }
}
}


