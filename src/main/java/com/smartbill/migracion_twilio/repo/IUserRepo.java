package com.smartbill.migracion_twilio.repo;

import com.smartbill.migracion_twilio.model.User;

import java.util.Optional;

public interface IUserRepo extends IGenericRepo<User, Integer>{

    //@Query("FROM User u WHERE u.username = :username)
    //DerivedQuery
    User findOneByUsername(String username);
    Optional<User> findByUsername(String username);

}
