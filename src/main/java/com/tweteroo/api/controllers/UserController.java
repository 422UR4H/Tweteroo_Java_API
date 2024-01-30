package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.services.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("users")
public class UserController {
  final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<Object> postUser(@RequestBody @Valid UserDTO entity) {
    Optional<UserModel> user = userService.create(entity);

    if (!user.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Repeated username");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
  }

  @GetMapping
  public List<UserModel> getAllUsers() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getUserById(@PathVariable("id") @NonNull Long id) {
    Optional<UserModel> user = userService.findById(id);

    if (!user.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(user.get());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable("id") @NonNull Long id) {
    Optional<UserModel> user = userService.deleteById(id);

    if (!user.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> putUser(@PathVariable("id") @NonNull Long id, @RequestBody @Valid UserDTO entity) {
    if (!userService.findById(id).isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    UserModel user = userService.update(id, entity);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

}
