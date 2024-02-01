package com.tweteroo.api.services;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.exceptions.ConflictUsernameException;
import com.tweteroo.api.exceptions.UserNotFoundException;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.UserRepository;

@Service
public class UserService {
  final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserModel create(UserDTO dto) {
    if (userRepository.existsByUsername(dto.getUsername())) {
      throw new ConflictUsernameException();
    }
    return userRepository.save(new UserModel(dto));
  }

  public List<UserModel> findAll() {
    return userRepository.findAll();
  }

  public UserModel findById(@NonNull Long id) {
    return userRepository.findById(id).orElseThrow(() -> {
      throw new UserNotFoundException();
    });
  }

  public UserModel deleteById(@NonNull Long id) {
    UserModel user = userRepository.findById(id).orElseThrow(() -> {
      throw new UserNotFoundException();
    });
    userRepository.deleteById(id);
    return user;
  }

  public UserModel update(@NonNull Long id, UserDTO dto) {
    UserModel user = new UserModel(dto);
    user.setId(id);

    return userRepository.save(user);
  }
}
