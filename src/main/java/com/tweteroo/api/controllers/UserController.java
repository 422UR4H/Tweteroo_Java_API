package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.UserRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> postUser(@RequestBody @Valid UserDTO entity) {
    UserModel user = userRepository.save(new UserModel(entity));
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping
  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getUserById(@PathVariable("id") @NonNull Long id) {
    Optional<UserModel> user = userRepository.findById(id);

    if (!user.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(user.get());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable("id") @NonNull Long id) {
    Optional<UserModel> user = userRepository.findById(id);

    if (!user.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    userRepository.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> putUser(@PathVariable("id") @NonNull Long id, @RequestBody @Valid UserDTO entity) {
    if (!userRepository.findById(id).isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    UserModel newUser = new UserModel(entity);
    newUser.setId(id);

    UserModel response = userRepository.save(newUser);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
