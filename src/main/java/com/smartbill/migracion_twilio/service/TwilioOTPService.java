package com.smartbill.migracion_twilio.service;

import com.smartbill.migracion_twilio.dto.PasswordResetRequestDTO;
import com.smartbill.migracion_twilio.dto.PasswordResetResponseDTO;

public interface TwilioOTPService {

    // Metodo para enviar un OTP para restablecimiento de contraseña
    PasswordResetResponseDTO sendOTPForPasswordReset(PasswordResetRequestDTO passwordResetRequestDTO);

    // Metodo para validar el OTP
    boolean validateOTP(String userInputOtp, String username);
}

