package com.smartbill.migracion_twilio.controller;

import com.smartbill.migracion_twilio.dto.OtpStatus;
import com.smartbill.migracion_twilio.dto.PasswordResetRequestDTO;
import com.smartbill.migracion_twilio.dto.PasswordResetResponseDTO; // Asegúrate de importar este DTO
import com.smartbill.migracion_twilio.service.impl.TwilioOTPServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "twilio")
@RestController
public class TwilioController {

    @Autowired
    private TwilioOTPServiceImpl service;

    // Metodo para enviar OTP
    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestBody PasswordResetRequestDTO dto) {
        // Llama al servicio para enviar el OTP
        PasswordResetResponseDTO response = service.sendOTPForPasswordReset(dto);

        // Verificar el estado del envío
        if (response.getStatus() == OtpStatus.DELIVERED) {
            return ResponseEntity.ok("OTP sent successfully");
        } else if (response.getStatus() == OtpStatus.USER_NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP: " + response.getMessage());
        }
    }

    // Metodo para validar OTP
    @PostMapping("/validate")
    public ResponseEntity<String> validateOTP(@RequestBody PasswordResetRequestDTO dto) {
        // Llama al servicio para validar el OTP
        boolean isValid = service.validateOTP(dto.getOnetimepassword(), dto.getUsername());
        if (isValid) {
            return ResponseEntity.ok("OTP validated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }
    }
}
