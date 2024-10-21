package com.smartbill.migracion_twilio.service;

import com.smartbill.migracion_twilio.model.ResetMail;

public interface IResetMailService {

    ResetMail findByRandom(String random);
    void save(ResetMail resetMail);
    void delete(ResetMail resetMail);
}
