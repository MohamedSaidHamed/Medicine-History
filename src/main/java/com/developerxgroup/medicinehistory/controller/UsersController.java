/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.developerxgroup.medicinehistory.controller;

import com.developerxgroup.medicinehistory.entity.UserMobileToken;
import com.developerxgroup.medicinehistory.entity.Users;
import com.developerxgroup.medicinehistory.service.UserMobileTokenService;
import com.developerxgroup.medicinehistory.service.UsersService;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohamed.Hamed
 */
@RestController
public class UsersController {

    @Autowired
    UsersService service;
    @Autowired
    UserMobileTokenService tokenService;

    @PostMapping(value = "/getUserByToken/{token}")
    public ResponseEntity getUserByToken(@PathVariable String token) {
        try {
            UserMobileToken userToken = tokenService.getUserByToken(token);
            if (userToken != null) {
                return new ResponseEntity<>(userToken.getUid(), HttpStatus.CREATED);
            }
            return new ResponseEntity<>("INVALID_TOKEN", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerNewUser")
    public ResponseEntity registerNewUser(@Valid @RequestBody Users newUser) {
        try {
            System.out.println("=========================" + newUser.getFname());
            Users user = service.updateUser(newUser);
            if (user != null) {
                return new ResponseEntity<>("USER_CREATED_SUCCESSFULLY", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam(value = "email", required = false) String email, 
            @RequestParam(value = "password", required = false) String password) {
        try {
            Logger.getAnonymousLogger().info("=====================================>>>"+email+"=="+password);
            Users user = service.getUserByEmail(email);
            if (user != null) {
                if (user.getPassword().equals(password) && user.getActive().equals(true)) {
                    UserMobileToken currToken = tokenService.getTokenByUser(user);
                    if (currToken == null) {
                        currToken = new UserMobileToken();
                        currToken.setCreationDate(new Date());
                        currToken.setUid(user);
                        currToken.setToken(RandomStringUtils.randomAlphanumeric(55));
                        currToken = tokenService.updateUserToken(currToken);
                    } else {
                        currToken.setToken(RandomStringUtils.randomAlphanumeric(55));
                        currToken = tokenService.updateUserToken(currToken);
                    }
                    return new ResponseEntity<>(currToken, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("INVALID", HttpStatus.UNAUTHORIZED);
                }

            }
            return new ResponseEntity<>("USER_NOT_FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
