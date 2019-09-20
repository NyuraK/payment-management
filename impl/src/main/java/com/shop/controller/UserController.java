package com.shop.controller;

import com.shop.api.swagger.controllers.UserManagementApi;
import com.shop.api.swagger.models.UserDTO;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserManagementApi {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(Integer id) {
        return ResponseEntity.ok(service.getUser(id));
    }
}
