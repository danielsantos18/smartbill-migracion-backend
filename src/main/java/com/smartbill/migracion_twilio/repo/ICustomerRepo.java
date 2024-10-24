package com.smartbill.migracion_twilio.repo;

import com.smartbill.migracion_twilio.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICustomerRepo extends IGenericRepo<Customer, Integer> {


/*
    //JPQL Java Persistence Query Language
    @Query("FROM Consult c WHERE c.patient.dni = :dni OR LOWER(c.patient.firstName) LIKE %:fullname% OR LOWER(c.patient.lastName) LIKE %:fullname%")
    List<Customer> search(@Param("dni") String dni, String fullname);

    // >= | <
    // 01-08-24 | 29-08-24 +1
    @Query("FROM Consult c WHERE c.consultDate BETWEEN :date1 AND :date2")
    List<Customer> searchByDates(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);


    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<CustomerDTO> callProcedureOrFunctionProjection();
    */

    @Query(value = "CALL fn_list()", nativeQuery = true)
    List<Object[]> callProcedureOrFunctionNative();

    Optional<Customer> findByDocumentNumber(String documentNumber);

}