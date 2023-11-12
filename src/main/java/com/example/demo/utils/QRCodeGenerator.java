package com.example.demo.utils;

import com.example.demo.classroom.Classroom;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    public static boolean generateQRCode(Classroom classroom) {
        try {
            String qrCodePath = "/QRCode";
            String qrCodeName = qrCodePath + classroom.getSubject() + classroom.getId() + "-QRCODE.png";

            // Set up QR code writer
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            // Generate QR code
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeName, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Save QR code as an image
            Path path = FileSystems.getDefault().getPath(qrCodeName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return true; // Return true if successful
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        }
    }
}
