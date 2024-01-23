package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.UserRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("users")
public class UserController {
  final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping
  public void postUser(@RequestBody @Valid UserDTO entity) {
    userRepository.save(new UserModel(entity));
  }

  @GetMapping
  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<UserModel> getUserById(@PathVariable("id") @NonNull Long id) {
    Optional<UserModel> user = userRepository.findById(id);

    if (!user.isPresent()) {
      return Optional.empty();
    }
    return Optional.of(user.get());
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") @NonNull Long id) {
    userRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public void putUser(@PathVariable("id") @NonNull Long id, @RequestBody @Valid UserDTO entity) {
    Optional<UserModel> user = userRepository.findById(id);

    if (!user.isPresent()) {
      // return user.empty(); ==> TODO: it'll be treated soon
    }

    UserModel newUser = new UserModel(entity);
    newUser.setId(id);
    userRepository.save(newUser);
  }

}
