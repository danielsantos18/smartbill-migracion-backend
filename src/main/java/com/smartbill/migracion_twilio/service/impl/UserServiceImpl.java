package com.smartbill.migracion_twilio.service.impl;


import com.smartbill.migracion_twilio.model.User;
import com.smartbill.migracion_twilio.repo.IGenericRepo;
import com.smartbill.migracion_twilio.repo.IUserRepo;
import com.smartbill.migracion_twilio.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    private final IUserRepo repo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}
