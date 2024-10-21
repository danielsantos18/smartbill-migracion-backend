package com.smartbill.migracion_twilio.repo;

import com.smartbill.migracion_twilio.dto.IConsultProcDTO;
import com.smartbill.migracion_twilio.model.Customer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IConsultRepo extends IGenericRepo<Customer, Integer> {

    @Query(value = "CALL fn_list()", nativeQuery = true)
    List<Object[]> callProcedureOrFunctionNative();

    @Query(value = "CALL  fn_list()", nativeQuery = true)
    List<IConsultProcDTO> callProcedureOrFunctionProjection();
}
