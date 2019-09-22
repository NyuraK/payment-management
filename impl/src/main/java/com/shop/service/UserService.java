package com.shop.service;

import com.shop.api.swagger.models.UserDTO;
import com.shop.exception.UserNotFoundException;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setContact(userDTO.getContact());
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        return user;
    }

    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(repository.save(user));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = repository.findById(userDTO.getId()).orElse(null);
        if (user == null) throw new UserNotFoundException(userDTO.getId());
        user = convertToEntity(userDTO);
        repository.save(user);
        return convertToDTO(user);
    }

    public void deleteUser(Integer id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) throw new UserNotFoundException(id);
        repository.delete(user);
    }
}