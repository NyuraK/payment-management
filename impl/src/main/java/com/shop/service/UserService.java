package com.shop.service;

import com.shop.api.swagger.models.UserDTO;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository repository;
    public User getUser(int id) {
        return repository.findById(id).orElse(null);
    }

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO getUser(Integer id) {
        User user = repository.findById(id).orElse(null);
        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        return userDTO
                .id(user.getId())
                .name(user.getName())
                .contact(user.getContact());
    }
}