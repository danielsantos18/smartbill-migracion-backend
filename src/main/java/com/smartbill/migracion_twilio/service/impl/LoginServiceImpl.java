package com.smartbill.migracion_twilio.service.impl;

import com.smartbill.migracion_twilio.model.User;
import com.smartbill.migracion_twilio.repo.ILoginRepo;
import com.smartbill.migracion_twilio.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

    private final ILoginRepo repo;
    private final PasswordEncoder bcrypt;

    @Override
    public User checkUsername(String username) {
        return repo.checkUsername(username);
    }

    @Override
    public void changePassword(String password, String username) {
        repo.changePassword(bcrypt.encode(password), username);
    }
}
