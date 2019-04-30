/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developerxgroup.medicinehistory.service;

import com.developerxgroup.medicinehistory.entity.UserMobileToken;
import com.developerxgroup.medicinehistory.entity.Users;
import com.developerxgroup.medicinehistory.repository.UserMobileTokenRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mohamed.Hamed
 */
@Component
public class UserMobileTokenService {
    @Autowired
    UserMobileTokenRepository repository;
    
    public UserMobileToken getUserByToken(String  token) throws Exception{
        UserMobileToken userToken = repository.getByToken(token);
        return userToken;
    }
    public UserMobileToken getTokenByUser(Users  user) throws Exception{
        UserMobileToken userToken = repository.findByUid(user);
        return userToken;
    }
    public UserMobileToken updateUserToken(UserMobileToken token) throws Exception{
        return repository.save(token);
    }
    
}
