package com.smartbill.migracion_twilio.service.impl;

import com.smartbill.migracion_twilio.model.MediaFile;
import com.smartbill.migracion_twilio.repo.IGenericRepo;
import com.smartbill.migracion_twilio.repo.IMediaFileRepo;
import com.smartbill.migracion_twilio.service.IMediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaFileServiceImpl extends CRUDImpl<MediaFile, Integer> implements IMediaFileService {

    private final IMediaFileRepo repo;

    @Override
    protected IGenericRepo<MediaFile, Integer> getRepo() {
        return repo;
    }
}
