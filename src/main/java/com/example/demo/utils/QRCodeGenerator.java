package com.example.demo.utils;

import com.example.demo.classroom.Classroom;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
@Component
public class QRCodeGenerator {

     public boolean generateQRCode(Classroom classroom) {
        try {
            // Set up QR code writer
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            // Generate QR code`
            String qrCodeData = generateQRCodeData(classroom); // got data of a classroom
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Save QR code as an image
            String qrCodePath = "QRCode";
            String qrCodeName = classroom.getId() + classroom.getSubject() + "CLASS-QRCODE.png";
            Path directoryPath = FileSystems.getDefault().getPath(qrCodePath);
            Files.createDirectories(directoryPath); // Create the directory if it doesn't exist
            Path path = directoryPath.resolve(qrCodeName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return true; // Return true if successful
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }

    private String generateQRCodeData(Classroom classroom) {
        // Customize the data you want to include in the QR code
        return "Class ID: " + classroom.getId() +
                "\nClass Name: " + classroom.getClassName() +
                "\nProfessor: " + classroom.getProfessor() +
                "\nRoom Number: " + classroom.getRoomNumber() +
                "\n Students: " + classroom.getStudents() +
                "\n Subject" + classroom.getSubject();
    }
}
