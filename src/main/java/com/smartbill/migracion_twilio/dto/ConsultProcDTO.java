package com.smartbill.migracion_twilio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultProcDTO {
    private Integer quantity;
    private String  consultdate;
}
