package com.smartbill.migracion_twilio.service.impl;


import com.smartbill.migracion_twilio.model.Customer;
import com.smartbill.migracion_twilio.repo.ICustomerRepo;
import com.smartbill.migracion_twilio.repo.IGenericRepo;
import com.smartbill.migracion_twilio.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.io.FileOutputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends CRUDImpl<Customer, Integer> implements ICustomerService {


    private final ICustomerRepo repo;

    @Override
    protected IGenericRepo<Customer, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Customer> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
            return repo.findByDocumentNumber(documentNumber).isPresent();
    }
}
