package com.smartbill.migracion_twilio.service.impl;

import com.smartbill.migracion_twilio.config.TwilioConfig;
import com.smartbill.migracion_twilio.dto.OtpStatus;
import com.smartbill.migracion_twilio.dto.PasswordResetRequestDTO;
import com.smartbill.migracion_twilio.dto.PasswordResetResponseDTO;
import com.smartbill.migracion_twilio.model.User;
import com.smartbill.migracion_twilio.repo.IUserRepo;
import com.smartbill.migracion_twilio.service.TwilioOTPService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

// Clase que implementa el servicio para enviar OTP utilizando Twilio
@Service
public class TwilioOTPServiceImpl implements TwilioOTPService {

    @Autowired
    private TwilioConfig twilioConfig;

    @Autowired
    private IUserRepo userRepository; // Repositorio de usuarios

    // Mapa para almacenar OTPs temporales
    private final Map<String, String> otpMap = new HashMap<>();

    // Metodo para enviar un OTP para restablecimiento de contraseña
    public PasswordResetResponseDTO sendOTPForPasswordReset(PasswordResetRequestDTO passwordResetRequestDTO) {
        PasswordResetResponseDTO passwordResetResponseDTO;

        // Verificar si el usuario está registrado
        Optional<User> user = userRepository.findByUsername(passwordResetRequestDTO.getUsername());
        if (user.isEmpty()) {
            return new PasswordResetResponseDTO(OtpStatus.USER_NOT_FOUND, "El usuario no está registrado.");
        }

        try {
            // Crear objeto PhoneNumber para el destinatario
            PhoneNumber to = new PhoneNumber(passwordResetRequestDTO.getNumber());
            // Crear objeto PhoneNumber para el remitente
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

            // Generar un OTP aleatorio de 6 dígitos
            String otp = generateOTP();
            // Crear el mensaje que se enviará al cliente
            String otpMessage = "Dear customer, your OTP is: " + otp;

            // Enviar el mensaje utilizando la API de Twilio
            Message message = Message.creator(to, from, otpMessage).create();

            // Almacenar el OTP en el mapa con el nombre de usuario como clave
            otpMap.put(passwordResetRequestDTO.getUsername(), otp);

            // Crear la respuesta indicando que se entregó el OTP
            passwordResetResponseDTO = new PasswordResetResponseDTO(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception e) {
            // En caso de error, crear la respuesta indicando que falló el envío
            passwordResetResponseDTO = new PasswordResetResponseDTO(OtpStatus.FAILED, e.getMessage());
        }
        return passwordResetResponseDTO; // Devolver la respuesta
    }

    // Metodo para validar el OTP
    public boolean validateOTP(String userInputOtp, String username) {
        return userInputOtp.equals(otpMap.get(username));
    }

    // Metodo privado para generar un OTP de 6 dígitos
    private String generateOTP() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
