package com.smartbill.migracion_twilio.service.impl;


import com.smartbill.migracion_twilio.model.ResetMail;
import com.smartbill.migracion_twilio.repo.IResetMailRepo;
import com.smartbill.migracion_twilio.service.IResetMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetMailServiceImpl implements IResetMailService {

    private final IResetMailRepo repo;

    @Override
    public ResetMail findByRandom(String random) {
        return repo.findByRandom(random);
    }

    @Override
    public void save(ResetMail resetMail) {
        repo.save(resetMail);
    }

    @Override
    public void delete(ResetMail resetMail) {
        repo.delete(resetMail);
    }
}
