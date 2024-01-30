package com.tweteroo.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tweteroo.api.dtos.TweetDTO;
import com.tweteroo.api.models.TweetModel;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.TweetRepository;
import com.tweteroo.api.repositories.UserRepository;

@Service
public class TweetService {
  private final TweetRepository tweetRepository;
  private final UserRepository userRepository;

  public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
    this.tweetRepository = tweetRepository;
    this.userRepository = userRepository;
  }

  public Optional<TweetModel> create(TweetDTO dto) {
    Long userId = dto.getUserId();
    if (userId == null) {
      return Optional.empty();
    }

    Optional<UserModel> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      return Optional.empty();
    }
    return Optional.of(tweetRepository.save(new TweetModel(dto, user.get())));
  }

  public List<TweetModel> findAll() {
    return tweetRepository.findAll();
  }

  public List<TweetModel> findByUserId(Long userId) {
    return tweetRepository.findByUserId(userId);
  }
}
