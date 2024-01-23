package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.UserRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
  final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("users")
  public void postUser(@RequestBody @Valid UserDTO entity) {
    userRepository.save(new UserModel(entity));
  }

  @GetMapping("users")
  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }

}
