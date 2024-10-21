package com.smartbill.migracion_twilio.controller;


import com.cloudinary.Cloudinary;
import com.smartbill.migracion_twilio.dto.ConsultProcDTO;
import com.smartbill.migracion_twilio.dto.IConsultProcDTO;
import com.smartbill.migracion_twilio.model.MediaFile;
import com.smartbill.migracion_twilio.service.IConsultService;
import com.smartbill.migracion_twilio.service.IMediaFileService;
import com.smartbill.migracion_twilio.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {

    private final IConsultService service;
    private final IMediaFileService mfService;
    private final MapperUtil mapperUtil;
    private final Cloudinary cloudinary;

    @GetMapping("/callProcedureNative")
    public ResponseEntity<List<ConsultProcDTO>> callProcedureNative(){
        return ResponseEntity.ok(service.callProcedureOrFunctionNative());
    }

    @GetMapping("/callProcedureProjection")
    public ResponseEntity<List<IConsultProcDTO>> callProcedureProjection(){
        return ResponseEntity.ok(service.callProcedureOrFunctionProjection());
    }

    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE) //APPLICATION_PDF_VALUE o APPLICATION_OCTET_STREAM_VALUE
    public ResponseEntity<byte[]> generateReport() throws Exception{
        byte[] data = service.generateReport();

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/readFile/{idFile}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> readFile(@PathVariable("idFile") Integer idFile) throws Exception {
        byte[] data = mfService.findById(idFile).getContent();
        return ResponseEntity.ok(data);
    }

    @PostMapping(value = "/saveFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        //DB
        MediaFile mf = new MediaFile();
        mf.setContent(multipartFile.getBytes());
        mf.setFileName(multipartFile.getOriginalFilename());
        mf.setFileType(multipartFile.getContentType());

        mfService.save(mf);
        return ResponseEntity.ok().build();
    }

    private File convertToFile(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return file;
    }
}
