package com.smartbill.migracion_twilio.service;

import com.smartbill.migracion_twilio.dto.ConsultProcDTO;
import com.smartbill.migracion_twilio.dto.IConsultProcDTO;
import com.smartbill.migracion_twilio.model.Customer;

import java.util.List;

public interface IConsultService extends ICRUD<Customer, Integer> {
    List<ConsultProcDTO> callProcedureOrFunctionNative();

    List<IConsultProcDTO> callProcedureOrFunctionProjection();

    byte[] generateReport() throws Exception;

}

