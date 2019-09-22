package com.shop.controller;

import com.shop.api.swagger.controllers.UserManagementApi;
import com.shop.api.swagger.models.UserDTO;
import com.shop.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "User management")
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

    @Override
    public ResponseEntity<UserDTO> updateUser(Integer id, UserDTO userDTO) {
        return ResponseEntity.ok(service.updateUser(userDTO));
    }

    @Override
    public ResponseEntity<UserDTO> addUser(UserDTO userDTO) {
        return ResponseEntity.ok(service.createUser(userDTO));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
