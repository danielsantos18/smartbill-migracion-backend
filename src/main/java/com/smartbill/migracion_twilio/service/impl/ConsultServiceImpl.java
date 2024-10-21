package com.smartbill.migracion_twilio.service.impl;


import com.smartbill.migracion_twilio.dto.ConsultProcDTO;
import com.smartbill.migracion_twilio.dto.IConsultProcDTO;
import com.smartbill.migracion_twilio.model.Customer;
import com.smartbill.migracion_twilio.repo.IConsultRepo;
import com.smartbill.migracion_twilio.repo.IGenericRepo;
import com.smartbill.migracion_twilio.service.IConsultService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Customer, Integer> implements IConsultService {
    private final IConsultRepo consultRepo;

    @Override
    protected IGenericRepo<Customer, Integer> getRepo() {   return consultRepo;  }

    @Override
    public List<IConsultProcDTO> callProcedureOrFunctionProjection() {
        return consultRepo.callProcedureOrFunctionProjection();
    }

    @Override
    public List<ConsultProcDTO> callProcedureOrFunctionNative() {
        List<ConsultProcDTO> list = new ArrayList<>();

        consultRepo.callProcedureOrFunctionNative().forEach(e -> {
            ConsultProcDTO dto = new ConsultProcDTO();

            // Establecer la cantidad como el phone_number convertido
            String phoneNumberStr = String.valueOf(e[0]);
            Integer phoneNumber = phoneNumberStr != null && !phoneNumberStr.isEmpty() ? Integer.parseInt(phoneNumberStr) : null;

            dto.setQuantity(phoneNumber);
            dto.setConsultdate(String.valueOf(e[1]));

            list.add(dto);
        });
        return list;
    }

    @Override
    public byte[] generateReport() throws Exception {
        byte[] data;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("txt_title", "caam.ingenierias@gmail.com");

        File file = new ClassPathResource("/reports/consultas.jasper").getFile();
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), parameters, new JRBeanCollectionDataSource(callProcedureOrFunctionNative()));
        data = JasperExportManager.exportReportToPdf(print);

        return data;
    }

}
