package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

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
  public ResponseEntity<UserModel> postUser(@RequestBody @Valid UserDTO entity) {
    UserModel user = userService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping
  public List<UserModel> getAllUsers() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserModel> getUserById(@PathVariable("id") @NonNull Long id) {
    UserModel user = userService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserModel> deleteUser(@PathVariable("id") @NonNull Long id) {
    UserModel user = userService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserModel> putUser(@PathVariable("id") @NonNull Long id, @RequestBody @Valid UserDTO entity) {
    UserModel user = userService.update(id, entity);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

}
