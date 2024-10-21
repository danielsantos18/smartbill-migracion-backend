package com.smartbill.migracion_twilio.service.impl;

import com.smartbill.migracion_twilio.model.Menu;
import com.smartbill.migracion_twilio.repo.IGenericRepo;
import com.smartbill.migracion_twilio.repo.IMenuRepo;
import com.smartbill.migracion_twilio.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu, Integer> implements IMenuService {

    private final IMenuRepo repo;

    @Override
    protected IGenericRepo<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> getMenusByUsername(String username) {
        //String contextUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return repo.getMenusByUsername(username);
    }
}
