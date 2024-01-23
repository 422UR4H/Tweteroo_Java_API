package com.tweteroo.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dtos.UserDTO;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.UserRepository;

@Service
public class UserService {
  final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<UserModel> create(UserDTO dto) {
    if (userRepository.existsByUsername(dto.getUsername())) {
      return Optional.empty();
    }
    return Optional.of(userRepository.save(new UserModel(dto)));
  }

  public List<UserModel> findAll() {
    return userRepository.findAll();
  }

  public Optional<UserModel> findById(@NonNull Long id) {
    return userRepository.findById(id);
  }

  public Optional<UserModel> deleteById(@NonNull Long id) {
    Optional<UserModel> user = userRepository.findById(id);

    if (!user.isPresent()) {
      return Optional.empty();
    }
    userRepository.deleteById(id);
    return user;
  }

  public UserModel update(@NonNull Long id, UserDTO dto) {
    UserModel newUser = new UserModel(dto);
    newUser.setId(id);

    UserModel response = userRepository.save(newUser);
    return response;
  }
}
