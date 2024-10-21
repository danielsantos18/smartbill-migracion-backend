package com.smartbill.migracion_twilio.service;

import com.smartbill.migracion_twilio.model.User;

public interface ILoginService {

    User checkUsername(String username);
    void changePassword(String password, String username);
}
