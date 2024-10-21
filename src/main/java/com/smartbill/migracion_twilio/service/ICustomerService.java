package com.smartbill.migracion_twilio.service;


import com.smartbill.migracion_twilio.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICustomerService extends ICRUD<Customer, Integer> {

    Page<Customer> listPage(Pageable pageable);
    // Nuevo metodo para verificar la existencia del documentNumber
    boolean existsByDocumentNumber(String documentNumber);

}
