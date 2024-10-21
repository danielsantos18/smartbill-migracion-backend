package com.smartbill.migracion_twilio.service.impl;


import com.smartbill.migracion_twilio.model.Role;
import com.smartbill.migracion_twilio.repo.IGenericRepo;
import com.smartbill.migracion_twilio.repo.IRoleRepo;
import com.smartbill.migracion_twilio.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    private final IRoleRepo repo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }
}
