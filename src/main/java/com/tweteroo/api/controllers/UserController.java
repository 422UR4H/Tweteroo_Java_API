package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.UserRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {
  final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("users")
  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }

}
