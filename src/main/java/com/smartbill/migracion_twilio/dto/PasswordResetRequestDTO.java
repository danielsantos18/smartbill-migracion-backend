package com.smartbill.migracion_twilio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetRequestDTO {
    private String number; //destination
    private String username;
    private String onetimepassword;
}
