/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developerxgroup.medicinehistory.service;

import com.developerxgroup.medicinehistory.entity.Users;
import com.developerxgroup.medicinehistory.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mohamed.Hamed
 */
@Component
public class UsersService {
    @Autowired
    UsersRepository repository;
    
    public List<Users> findAllUsers() throws Exception{
        return repository.findAll();
    }
    
    public Users updateUser(Users user) throws Exception{
        return repository.save(user);
    }
    
    public Users getUserByEmail(String email) throws Exception{
        return repository.findByEmailAndActive(email, true);
    }
    
}
