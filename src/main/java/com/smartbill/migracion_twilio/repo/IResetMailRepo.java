package com.smartbill.migracion_twilio.repo;

import com.smartbill.migracion_twilio.model.ResetMail;

public interface IResetMailRepo extends IGenericRepo<ResetMail, Integer> {

    //FROM ResetMail rm WHERE rm.random = ?
    ResetMail findByRandom(String random);
}
