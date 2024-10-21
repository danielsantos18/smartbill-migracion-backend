package com.smartbill.migracion_twilio.service;

import com.smartbill.migracion_twilio.model.Menu;

import java.util.List;

public interface IMenuService {

    List<Menu> getMenusByUsername(String username);

}